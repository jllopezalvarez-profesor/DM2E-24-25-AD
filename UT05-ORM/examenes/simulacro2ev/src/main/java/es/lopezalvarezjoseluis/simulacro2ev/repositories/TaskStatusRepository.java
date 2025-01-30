package es.lopezalvarezjoseluis.simulacro2ev.repositories;

import es.lopezalvarezjoseluis.simulacro2ev.entities.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, Integer> {
}
