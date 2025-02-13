package es.lopezalvarezjoseluis.examenes.examen2evresuelto.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
// Añadido al enunciado: no se puede repetir la combinación de nombre y apellido
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"first_name", "last_name"})})
public class Programmer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer programmerId;

    // Añadido al enunciado: no puede haber dos programadores con el mismo DNI
    @Column(nullable = false, length = 20, unique = true)
    private String documentNumber;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @ManyToOne
    // Modificado sobre enunciado: los programadores pueden no estar asociados a una categoría.
    @JoinColumn(name = "category_id", nullable = true)
    private Category category;

    @ManyToMany
    @JoinTable(name = "project_programmer",
            joinColumns = @JoinColumn(name="programmer_id"),
            inverseJoinColumns = @JoinColumn(name="project_id"))
//    @JsonIgnore
    @Getter(AccessLevel.MODULE)
    private Set<Project> projects;
}
