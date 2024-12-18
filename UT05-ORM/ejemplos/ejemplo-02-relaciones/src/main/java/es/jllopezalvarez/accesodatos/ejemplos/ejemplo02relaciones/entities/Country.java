package es.jllopezalvarez.accesodatos.ejemplos.ejemplo02relaciones.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "country")
public class Country {
    @Id
    @Column(name = "country_id", nullable = false)
    private Short id;

    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @ColumnDefault("current_timestamp()")
    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

    @OneToMany
    // Como JPA trabaja con convenciones, y hemos quitado toda la
    // configuración de la relación en "City", pues tenemos que
    // indicar en esta relación que columna de "City" se usa para
    // clave ajena hacia Country
    @JoinColumn(name = "country_id", nullable = false)
    private Set<City> cities = new LinkedHashSet<>();

}