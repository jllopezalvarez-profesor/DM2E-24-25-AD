package es.lopezalvarezjoseluis.simulacro2ev.repositories;

import es.lopezalvarezjoseluis.simulacro2ev.entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    public Page<Task> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrderByTitle (String title, String description, Pageable pageable);
}
