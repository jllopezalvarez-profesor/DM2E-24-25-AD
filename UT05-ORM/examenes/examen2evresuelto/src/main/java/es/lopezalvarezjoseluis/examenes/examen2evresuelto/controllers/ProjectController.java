package es.lopezalvarezjoseluis.examenes.examen2evresuelto.controllers;

import es.lopezalvarezjoseluis.examenes.examen2evresuelto.entities.Project;
import es.lopezalvarezjoseluis.examenes.examen2evresuelto.services.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/min-expected-income/{expectedIncome}")
    public ResponseEntity<Iterable<Project>> getProjectByMinIncome(@PathVariable("expectedIncome") Double expectedIncome) {
        return ResponseEntity.ok(projectService.findByMinIncome(expectedIncome));
    }

}
