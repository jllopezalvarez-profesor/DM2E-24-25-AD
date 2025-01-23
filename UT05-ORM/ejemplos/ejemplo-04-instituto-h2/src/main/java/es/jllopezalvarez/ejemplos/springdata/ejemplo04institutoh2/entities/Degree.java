package es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="degrees")
public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer degreeId;
    @Column(length = 200, nullable = false)
    private String name;
    @Column (nullable = false)
    private Integer totalHours;

    @OneToMany(mappedBy = "degree")
    private List<Module> modules;

    public Degree() {
        this.modules = new ArrayList<>();
    }

    public Degree(Integer degreeId, String name, Integer totalHours) {
        this.degreeId = degreeId;
        this.name = name;
        this.totalHours = totalHours;
        this.modules = new ArrayList<>();
    }

    public Integer getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(Integer degreeId) {
        this.degreeId = degreeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Integer totalHours) {
        this.totalHours = totalHours;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
}
