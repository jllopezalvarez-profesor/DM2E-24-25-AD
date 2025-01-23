package es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.services;

import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.dto.CreateDegreeAndModulesDto;
import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.dto.CreateModuleDto;
import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.entities.Degree;
import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.entities.Module;
import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.repositories.DegreeRepository;
import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.repositories.ModuleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DegreeServiceImpl implements DegreeService {

    private final ModelMapper modelMapper;
    private final DegreeRepository degreeRepository;
    private final ModuleRepository moduleRepository;

    public DegreeServiceImpl(ModelMapper modelMapper, DegreeRepository degreeRepository, ModuleRepository moduleRepository) {
        this.modelMapper = modelMapper;
        this.degreeRepository = degreeRepository;
        this.moduleRepository = moduleRepository;
    }

    @Override
    @Transactional
    public void createDegreeAndModules(CreateDegreeAndModulesDto createDegreeDto) {

        Degree degree = new Degree();
        modelMapper.map(createDegreeDto, degree);
        // Manualmente:
//        degree.setName(createDegreeDto.getName());
//        degree.setTotalHours(createDegreeDto.getTotalHours());
        degreeRepository.save(degree);

        // Lanzamos excepción para comprobar si no se crea nada
        // Lo incluimos en este if siempre verdadero para que Java no
        // se queje porque hay código no alcanzable más abajo.
        if (degree != null) {
            throw new RuntimeException("Excepción intencionada para que falle a medias");
        }

        for(CreateModuleDto createModuleDto : createDegreeDto.getModules()){
            Module module = new Module();
            modelMapper.map(createModuleDto, module);
            module.setDegree(degree);
            moduleRepository.save(module);
        }
    }
}
