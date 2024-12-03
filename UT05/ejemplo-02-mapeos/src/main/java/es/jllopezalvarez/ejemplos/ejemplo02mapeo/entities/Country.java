package es.jllopezalvarez.ejemplos.ejemplo02mapeo.entities;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @Column(name = "country_id", nullable = false)
    private Short id;

    @Column(name = "country", nullable = false, length = 50)
    private String pais;

    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

//    @OneToMany(mappedBy = "country")
//    private Set<City> cities = new LinkedHashSet<>();

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Instant getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Instant lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

//    public Set<City> getCities() {
//        return cities;
//    }
//
//    public void setCities(Set<City> cities) {
//        this.cities = cities;
//    }

}