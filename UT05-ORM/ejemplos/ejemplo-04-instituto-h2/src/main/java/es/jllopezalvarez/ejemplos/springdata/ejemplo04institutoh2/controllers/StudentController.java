package es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.controllers;


import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.entities.Student;
import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.repositories.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @RequestMapping("")
    public ResponseEntity<List<Student>> getAllStudents() {
//        List<Student> students = new ArrayList<>();
//        students.add(new Student(1, "José Luis", "López Álvarez", LocalDate.of(1972, 8, 13)));
//        students.add(new Student(2, "Mónica", "López Lamela", LocalDate.of(2006, 4, 14)));
//
//        return ResponseEntity.ok(students);


        return ResponseEntity.ok(studentRepository.findAll());
    }


//    @RequestMapping(value = "/api/students/{id}", method = RequestMethod.GET)
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Integer id) {
        return ResponseEntity.notFound().build();
    }

//    @RequestMapping(value = "/api/students/{id}", method = RequestMethod.DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudentById(@PathVariable("id") Integer id) {
        return ResponseEntity.notFound().build();
    }




}
