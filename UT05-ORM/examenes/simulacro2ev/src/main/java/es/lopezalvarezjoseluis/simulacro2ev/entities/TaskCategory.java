package es.lopezalvarezjoseluis.simulacro2ev.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class TaskCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskCategoryId;

    @Column(nullable = false, length = 30)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TaskCategory that = (TaskCategory) o;
        return Objects.equals(taskCategoryId, that.taskCategoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(taskCategoryId);
    }

    public Integer getTaskCategoryId() {
        return taskCategoryId;
    }

    public void setTaskCategoryId(Integer taskCategoryId) {
        this.taskCategoryId = taskCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
