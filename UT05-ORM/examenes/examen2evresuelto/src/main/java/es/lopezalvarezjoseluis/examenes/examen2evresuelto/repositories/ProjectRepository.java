package es.lopezalvarezjoseluis.examenes.examen2evresuelto.repositories;

import es.lopezalvarezjoseluis.examenes.examen2evresuelto.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findAllByExpectedIncomeGreaterThanEqualOrderByNameDesc(double expectedIncome);

//    Optional<Project>

}
