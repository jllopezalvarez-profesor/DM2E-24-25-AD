package es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.repositories;

import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.entities.Degree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DegreeRepository extends JpaRepository<Degree, Long> {
}
