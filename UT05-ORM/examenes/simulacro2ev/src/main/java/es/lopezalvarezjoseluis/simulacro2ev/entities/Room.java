package es.lopezalvarezjoseluis.simulacro2ev.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer roomId;
    @Column(nullable = false, length = 50)
    private String name;
    @ManyToMany(mappedBy = "rooms")
    private Set<Task> tasks;
}
