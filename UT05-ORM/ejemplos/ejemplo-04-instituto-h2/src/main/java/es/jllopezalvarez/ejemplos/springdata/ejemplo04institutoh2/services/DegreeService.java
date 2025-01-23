package es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.services;

import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.dto.CreateDegreeAndModulesDto;

public interface DegreeService {
    void createDegreeAndModules(CreateDegreeAndModulesDto createDegreeDto);
}
