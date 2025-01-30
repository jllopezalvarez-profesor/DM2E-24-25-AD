package es.lopezalvarezjoseluis.simulacro2ev.services;

import es.lopezalvarezjoseluis.simulacro2ev.dto.NewTaskAssignmentDto;

public interface TaskAssignmentService {
    void CreateTaskAssignment(NewTaskAssignmentDto taskAssignmentDto);

    void CompleteTaskAssignment(Integer taskAssignmentId);

    boolean ExistsById(Integer taskAssignmentId);

    boolean IsCompleted(Integer taskAssignmentId);
}
