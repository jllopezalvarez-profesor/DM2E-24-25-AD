package es.lopezalvarezjoseluis.examenes.examen2evresuelto.services;

import es.lopezalvarezjoseluis.examenes.examen2evresuelto.entities.Project;

import java.util.List;

public interface ProjectService {
    List<Project> findByMinIncome(Double minIncome);
}
