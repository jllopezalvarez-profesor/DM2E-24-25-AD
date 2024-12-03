package es.jllopezalvarez.ejemplos.ejemplo02mapeo.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "city", indexes = {
        @Index(name = "idx_fk_country_id", columnList = "country_id")
})
public class City {
    @Id
    @Column(name = "city_id", nullable = false)
    private Short id;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;


    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public es.jllopezalvarez.ejemplos.ejemplo02mapeo.entities.Country getCountry() {
        return country;
    }

    public void setCountry(es.jllopezalvarez.ejemplos.ejemplo02mapeo.entities.Country country) {
        this.country = country;
    }

    public Instant getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Instant lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

}