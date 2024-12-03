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
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "category")
public class Category {
    @Id
    @Column(name = "category_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private Byte categoryId;

    @Column(name = "name", nullable = false, length = 25)
    @ToString.Include
    private String name;

    @ColumnDefault("current_timestamp()")
    @Column(name = "last_update", nullable = false, updatable = false, insertable = false)
    private Instant lastUpdate;

    @ManyToMany
    @JoinTable(name = "film_category", joinColumns = {@JoinColumn(name = "category_id")}, inverseJoinColumns = {@JoinColumn(name = "film_id")})
    private Collection<Film> films;
}