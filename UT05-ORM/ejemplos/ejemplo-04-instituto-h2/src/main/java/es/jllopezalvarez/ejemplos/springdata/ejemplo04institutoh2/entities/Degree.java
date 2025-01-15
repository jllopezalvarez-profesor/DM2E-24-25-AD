package es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.entities;


import jakarta.persistence.*;

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
}
