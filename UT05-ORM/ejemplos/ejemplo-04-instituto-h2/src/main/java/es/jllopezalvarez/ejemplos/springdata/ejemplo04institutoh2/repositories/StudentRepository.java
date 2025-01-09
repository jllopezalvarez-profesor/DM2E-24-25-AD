package es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.repositories;

import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
