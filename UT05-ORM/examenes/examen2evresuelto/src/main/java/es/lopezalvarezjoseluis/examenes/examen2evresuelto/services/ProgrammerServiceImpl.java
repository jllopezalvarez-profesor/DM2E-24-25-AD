package es.lopezalvarezjoseluis.examenes.examen2evresuelto.services;

import es.lopezalvarezjoseluis.examenes.examen2evresuelto.NewProgrammerDto;
import es.lopezalvarezjoseluis.examenes.examen2evresuelto.entities.Category;
import es.lopezalvarezjoseluis.examenes.examen2evresuelto.entities.Programmer;
import es.lopezalvarezjoseluis.examenes.examen2evresuelto.exceptions.DocumentNumberWrongLengthException;
import es.lopezalvarezjoseluis.examenes.examen2evresuelto.repositories.CategoryRepository;
import es.lopezalvarezjoseluis.examenes.examen2evresuelto.repositories.ProgrammerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProgrammerServiceImpl implements ProgrammerService {
    private final CategoryRepository categoryRepository;
    private final ProgrammerRepository programmerRepository;

    public ProgrammerServiceImpl(CategoryRepository categoryRepository, ProgrammerRepository programmerRepository) {
        this.categoryRepository = categoryRepository;
        this.programmerRepository = programmerRepository;
    }

    @Override
    public void createProgrammer(NewProgrammerDto newProgrammerDto) {
        // Asumo inicialmente que el programado no tiene categoría
        Category programmerCategory = null;

        // Si tiene categoría, buscarla y si no se encuentra, error
        if (newProgrammerDto.getCategoryId() != null) {
            programmerCategory = categoryRepository
                    .findById(newProgrammerDto.getCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException(String.format("No se encuentra la categoría con id %d", newProgrammerDto.getCategoryId())));
        }

        // Añadido tras al enunciado original
        // Comprobar que el número de documento tiene entre 8 y 10 caracteres
        String documentNumber = newProgrammerDto.getDocumentNumber();
        if (documentNumber== null || documentNumber.length()<8 || documentNumber.length()>10) {
            throw new DocumentNumberWrongLengthException(documentNumber);
        }

        // Crear el programador

        Programmer programmer = new Programmer();
        programmer.setFirstName(newProgrammerDto.getFirstName());
        programmer.setLastName(newProgrammerDto.getLastName());
        programmer.setDocumentNumber(newProgrammerDto.getDocumentNumber());
        programmer.setCategory(programmerCategory);

        programmerRepository.save(programmer);
    }

    @Override
    public void deleteById(Integer programmerId) {
        if (!programmerRepository.existsById(programmerId)) {
            throw new EntityNotFoundException(String.format("No existe el programador con id %d", programmerId));
        }
        programmerRepository.deleteById(programmerId);
    }

}
