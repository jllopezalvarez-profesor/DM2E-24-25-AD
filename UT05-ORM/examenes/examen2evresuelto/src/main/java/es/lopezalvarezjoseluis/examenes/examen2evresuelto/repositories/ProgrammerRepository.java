package es.lopezalvarezjoseluis.examenes.examen2evresuelto.repositories;

import es.lopezalvarezjoseluis.examenes.examen2evresuelto.entities.Programmer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgrammerRepository extends JpaRepository<Programmer, Integer> {


}
