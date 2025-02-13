package es.lopezalvarezjoseluis.examenes.examen2evresuelto.controllers;

import es.lopezalvarezjoseluis.examenes.examen2evresuelto.NewProgrammerDto;
import es.lopezalvarezjoseluis.examenes.examen2evresuelto.services.ProgrammerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/programmers")
public class ProgrammerController {

    private final ProgrammerService programmerService;

    public ProgrammerController(ProgrammerService programmerService) {
        this.programmerService = programmerService;
    }

    @PostMapping
    public ResponseEntity<String> createConControllerAdvice(@RequestBody NewProgrammerDto newProgrammerDto) {
        programmerService.createProgrammer(newProgrammerDto);
        return ResponseEntity.ok("Programador creado");
    }

    public ResponseEntity<String> create(@RequestBody NewProgrammerDto newProgrammerDto) {
        try {
            programmerService.createProgrammer(newProgrammerDto);
            return ResponseEntity.ok("Programador creado");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{programmerId}")
    public ResponseEntity<String> deleteConControllerAdvice(@PathVariable Integer programmerId) {
        programmerService.deleteById(programmerId);
        return ResponseEntity.ok("Programador eliminado");
    }

    public ResponseEntity<String> delete(@PathVariable Integer programmerId) {
        try {
            programmerService.deleteById(programmerId);
            return ResponseEntity.ok("Programador eliminado");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
