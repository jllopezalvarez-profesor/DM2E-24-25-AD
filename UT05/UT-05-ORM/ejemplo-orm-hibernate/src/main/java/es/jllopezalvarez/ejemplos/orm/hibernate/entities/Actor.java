package es.jllopezalvarez.ejemplos.orm.hibernate.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "actor")
@ToString(onlyExplicitlyIncluded = true)
public class Actor {
    @Id
    @Column(name = "actor_id", nullable = false)
    @ToString.Include
    private Short actorId;

    @Column(name = "first_name", nullable = false, length = 45)
    @ToString.Include
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 45)
    @ToString.Include
    private String lastName;

    @ColumnDefault("current_timestamp()")
    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

    @ManyToMany
    @JoinTable(name = "film_actor", joinColumns = {@JoinColumn(name = "actor_id")}, inverseJoinColumns = {@JoinColumn(name = "film_id")})
    private Collection<Film> films;
}