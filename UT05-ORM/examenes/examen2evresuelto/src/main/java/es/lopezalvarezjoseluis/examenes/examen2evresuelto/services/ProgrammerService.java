package es.lopezalvarezjoseluis.examenes.examen2evresuelto.services;

import es.lopezalvarezjoseluis.examenes.examen2evresuelto.NewProgrammerDto;

public interface ProgrammerService {
    void createProgrammer(NewProgrammerDto newProgrammerDto);

    void deleteById(Integer programmerId);
}
