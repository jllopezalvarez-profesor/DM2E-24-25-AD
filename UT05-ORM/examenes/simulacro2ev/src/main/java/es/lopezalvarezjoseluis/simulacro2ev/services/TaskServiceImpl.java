package es.lopezalvarezjoseluis.simulacro2ev.services;

import es.lopezalvarezjoseluis.simulacro2ev.entities.Task;
import es.lopezalvarezjoseluis.simulacro2ev.repositories.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findTasks(String search, int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize, Sort.by("title"));
        Page<Task> results = taskRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrderByTitle(search, search, pageRequest);
        return results.getContent();
    }
}
