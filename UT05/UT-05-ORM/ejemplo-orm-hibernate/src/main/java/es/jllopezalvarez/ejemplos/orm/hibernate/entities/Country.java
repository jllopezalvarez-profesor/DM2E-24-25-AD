package es.jllopezalvarez.ejemplos.orm.hibernate.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "country")
public class Country {
    @Id
    @Column(name = "country_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private Short countryId;

    @Column(name = "country", nullable = false, length = 50)
    @ToString.Include
    private String name;

    @ColumnDefault("current_timestamp()")
    @Column(name = "last_update", nullable = false, insertable = false, updatable = false)
    private Instant lastUpdate;

    @OneToMany(mappedBy = "country")
    private Set<City> cities = new LinkedHashSet<>();

}