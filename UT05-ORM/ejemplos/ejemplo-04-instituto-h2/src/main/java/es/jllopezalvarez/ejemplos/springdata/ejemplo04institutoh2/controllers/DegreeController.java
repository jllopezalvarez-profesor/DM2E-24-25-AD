package es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.controllers;

import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.dto.CreateDegreeAndModulesDto;
import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.entities.Degree;
import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.services.DegreeService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/degrees")
public class DegreeController {

    private final DegreeService degreeService;

    public DegreeController(DegreeService degreeService) {
        this.degreeService = degreeService;
    }

    @PostMapping
    public ResponseEntity<?> createDegree(@RequestBody CreateDegreeAndModulesDto createDegreeDto) {
        degreeService.createDegreeAndModules(createDegreeDto);
        return ResponseEntity.created(null).build();
    }

}
