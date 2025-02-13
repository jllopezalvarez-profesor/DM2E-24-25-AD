package es.lopezalvarezjoseluis.examenes.examen2evresuelto.services;

import es.lopezalvarezjoseluis.examenes.examen2evresuelto.entities.Project;
import es.lopezalvarezjoseluis.examenes.examen2evresuelto.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> findByMinIncome(Double minIncome) {
        return projectRepository.findAllByExpectedIncomeGreaterThanEqualOrderByNameDesc(minIncome);
    }
}
