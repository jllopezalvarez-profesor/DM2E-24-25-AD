package es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.controllers;


import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.entities.Student;
import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {


    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping({"", "/"})
    public ResponseEntity<List<Student>> getAllStudents() {
//        List<Student> students = new ArrayList<>();
//        students.add(new Student(1, "José Luis", "López Álvarez", LocalDate.of(1972, 8, 13)));
//        students.add(new Student(2, "Mónica", "López Lamela", LocalDate.of(2006, 4, 14)));
//
//        return ResponseEntity.ok(students);


        return ResponseEntity.ok(studentService.findAll());
    }


    //    @RequestMapping(value = "/api/students/{id}", method = RequestMethod.GET)
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Integer id) {
        System.out.println("En getStudentById, id = " + id);

        Optional<Student> student =  studentService.findById(id);
        if (student.isPresent()) {
            return ResponseEntity.ok(student.orElseThrow());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //    @RequestMapping(value = "/api/students/{id}", method = RequestMethod.DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudentById(@PathVariable("id") Integer id) {
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/{page}/{pageSize}")
    public ResponseEntity<List<Student>> findAllPaged(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize ) {
        return ResponseEntity.ok(studentService.findAllPaged(page, pageSize));
    }


}
