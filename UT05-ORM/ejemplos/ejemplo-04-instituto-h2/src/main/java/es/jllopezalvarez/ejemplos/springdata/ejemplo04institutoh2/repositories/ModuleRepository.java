package es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.repositories;

import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module, Long> {
}
