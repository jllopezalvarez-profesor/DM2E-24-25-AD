package es.lopezalvarezjoseluis.simulacro2ev.services;

import es.lopezalvarezjoseluis.simulacro2ev.entities.Task;

import java.util.List;

public interface TaskService {
    List<Task> findTasks(String search, int pageNumber, int pageSize);
}
