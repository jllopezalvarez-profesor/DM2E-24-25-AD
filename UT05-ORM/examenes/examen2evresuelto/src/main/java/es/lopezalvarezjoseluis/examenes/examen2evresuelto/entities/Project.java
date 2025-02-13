package es.lopezalvarezjoseluis.examenes.examen2evresuelto.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectId;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false, length = 200)
    private String clientName;

    @Column(nullable = false)
    private Double expectedIncome;

    private LocalDate startDate;

    @ManyToMany(mappedBy = "projects")
    private List<Programmer> programmers;

}
