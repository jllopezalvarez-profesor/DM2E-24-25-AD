package es.lopezalvarezjoseluis.simulacro2ev.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class TaskAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskAssignmentId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime assignmentDatetime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private LocalDateTime completionDatetime;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @ManyToOne
    @JoinColumn(name = "task_status_id", nullable = false)
    private TaskStatus taskStatus;

    @ManyToOne
    @JoinColumn(name = "family_member_id", nullable = false)
    private FamilyMember familyMember;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TaskAssignment that = (TaskAssignment) o;
        return taskAssignmentId == that.taskAssignmentId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(taskAssignmentId);
    }

    public int getTaskAssignmentId() {
        return taskAssignmentId;
    }

    public void setTaskAssignmentId(int taskAssignmentId) {
        this.taskAssignmentId = taskAssignmentId;
    }

    public LocalDateTime getAssignmentDatetime() {
        return assignmentDatetime;
    }

    public void setAssignmentDatetime(LocalDateTime assignmentDatetime) {
        this.assignmentDatetime = assignmentDatetime;
    }

    public LocalDateTime getCompletionDatetime() {
        return completionDatetime;
    }

    public void setCompletionDatetime(LocalDateTime completionDatetime) {
        this.completionDatetime = completionDatetime;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public FamilyMember getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(FamilyMember familyMember) {
        this.familyMember = familyMember;
    }
}
