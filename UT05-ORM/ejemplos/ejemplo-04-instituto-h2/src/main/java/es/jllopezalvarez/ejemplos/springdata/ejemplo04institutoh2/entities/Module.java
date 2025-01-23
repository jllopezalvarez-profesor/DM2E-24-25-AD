package es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity

@Table(name="modules")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer moduleId;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer anualHours;

    @ManyToMany(mappedBy = "modules")
    @JsonBackReference
    private List<Student> students;

    @ManyToOne
    @JoinColumn(name = "degree_id", nullable = false)
    private Degree degree;

    public Integer getModuleId() {
        return moduleId;
    }

    public String getName() {
        return name;
    }

    public Integer getAnualHours() {
        return anualHours;
    }

    public List<Student> getStudents() {
        return students;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAnualHours(Integer anualHours) {
        this.anualHours = anualHours;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }
}
