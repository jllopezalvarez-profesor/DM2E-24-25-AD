package es.jllopezalvarez.spring.ejemplos.ejemplo01spring.controllers;

import es.jllopezalvarez.spring.ejemplos.ejemplo01spring.entities.Person;
import net.datafaker.Faker;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequestMapping("/api/people")
public class PersonController {

    private final Faker faker = new Faker();

    @GetMapping
    public String getSomething() {
        return "Hello!!!";
    }

    @GetMapping("/other")
    public String getOtherThing() {
        return "Goodbye!!!";
    }

    @GetMapping("/{personId}")
    public ResponseEntity<Person> getById(@PathVariable("personId") Integer id) {

        if (id < 0 || id > 10) {
            // No se encuentra la persona
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new Person(
                id,
                faker.name().firstName(),
                faker.name().lastName()
        ));
    }

}
