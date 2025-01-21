package es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.repositories;

import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.dto.StudentDtoClass;
import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.dto.StudentDtoInterfaceDefault;
import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.dto.StudentDtoInterfaceSpEL;
import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.dto.StudentDtoRecord;
import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findStudentsByFirstNameContainingIgnoreCase(String firstName);

    List<Student> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseOrderByStudentIdAsc(String firstName, String lastName);

    // SQL
    @Query(value = "select count(1) from students", nativeQuery = true)
    Long countStudentsWithSQL();

    @Query(value = "select * from students where first_name like %:name% or last_name like %:name%", nativeQuery = true)
    List<Student> findContainingInName(@Param("name") String studentName);


    // Proyección a StudentDto usando intefaces y SpEL (Spring Expression Language)
    List<StudentDtoInterfaceSpEL> findAllBy();

    // Proyección a StudentDto usando interfaces y métodos default
    List<StudentDtoInterfaceDefault> findAllByOrderByLastName();

    // Proyección a StudentDto usando clases
    List<StudentDtoClass> findAllByOrderByFirstNameDesc();

    // Proyección a StudentDto usando record
    List<StudentDtoRecord> findAllByOrderByLastNameAsc();


}
