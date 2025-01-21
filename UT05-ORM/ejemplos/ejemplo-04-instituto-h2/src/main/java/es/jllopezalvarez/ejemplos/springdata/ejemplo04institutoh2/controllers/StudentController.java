package es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.controllers;


import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.dto.StudentDtoClass;
import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.dto.StudentDtoInterfaceDefault;
import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.dto.StudentDtoInterfaceSpEL;
import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.dto.StudentDtoRecord;
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

        Optional<Student> student = studentService.findById(id);
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
    public ResponseEntity<List<Student>> findAllPaged(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        return ResponseEntity.ok(studentService.findAllPaged(page, pageSize));
    }


    @GetMapping("/search/{name}")
    public ResponseEntity<List<Student>> findByName(@PathVariable("name") String name) {
        List<Student> students = studentService.findByName(name);
        return ResponseEntity.ok(students);
    }


    @GetMapping("/search/{firstName}/{lastName}")
    public ResponseEntity<List<Student>> findByName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        List<Student> students = studentService.findByNameAndLastName(firstName, lastName);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/count-using-sql")
    public ResponseEntity<Long> countUsingSql() {
        return ResponseEntity.ok(studentService.countUsingSql());
    }

    @GetMapping("/find-containing-name/{name}")
    public ResponseEntity<List<Student>> findContainingName(@PathVariable("name") String name) {
        return ResponseEntity.ok(studentService.findContaningName(name));
    }

    @GetMapping("/get-all-dto-spel")
    public ResponseEntity<List<StudentDtoInterfaceSpEL>> getAllDtoSpel() {
        return ResponseEntity.ok(studentService.getAllDtoInterfaceSpEL());
    }

    @GetMapping("/get-all-dto-default")
    public ResponseEntity<List<StudentDtoInterfaceDefault>> getAllDtoDefault() {
        return ResponseEntity.ok(studentService.getAllDtoInterfaceDefault());
    }

    @GetMapping("/get-all-dto-class")
    public ResponseEntity<List<StudentDtoClass>> getAllDtoClass() {
        return ResponseEntity.ok(studentService.getAllDtoClass());
    }

    @GetMapping("/get-all-dto-record")
    public ResponseEntity<List<StudentDtoRecord>> getAllDtoRecord() {
//        StudentDtoRecord student = new StudentDtoRecord("A", "B");
        return ResponseEntity.ok(studentService.getAllDtoRecord());
    }

    @GetMapping("/get-all-dto-automapped")
    public ResponseEntity<List<StudentDtoClass>> getAllDtoAutoMapped() {
        return ResponseEntity.ok(studentService.getAllDtoClassMapped());
    }
}
