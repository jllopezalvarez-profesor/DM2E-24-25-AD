package es.jllopezalvarez.ejemplos.orm.hibernate.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "city")
public class City {
    @Id
    @Column(name = "city_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private Short cityId;

    @Column(name = "city", nullable = false, length = 50)
    @ToString.Include
    private String name;

    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ColumnDefault("current_timestamp()")
    @Column(name = "last_update", nullable = false, insertable = false, updatable = false)
    private Instant lastUpdate;

}