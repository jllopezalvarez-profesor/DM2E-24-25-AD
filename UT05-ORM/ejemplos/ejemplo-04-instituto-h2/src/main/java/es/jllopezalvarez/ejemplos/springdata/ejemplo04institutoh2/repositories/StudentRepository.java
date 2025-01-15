package es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.repositories;

import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.entities.Student;
import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findStudentsByFirstNameContainingIgnoreCase(String firstName);
    List<Student> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseOrderByStudentIdAsc  (String firstName, String lastName);

    // SQL
    @Query(value = "select count(1) from students", nativeQuery = true)
    Long countStudentsWithSQL();

    @Query(value = "select * from students where first_name like %:name% or last_name like %:name%", nativeQuery = true)
    List<Student> findContainingInName(@Param("name") String studentName);

    // JPQL

}
