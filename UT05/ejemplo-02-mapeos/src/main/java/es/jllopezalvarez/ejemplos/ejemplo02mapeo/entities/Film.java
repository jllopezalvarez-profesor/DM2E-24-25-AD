package es.jllopezalvarez.ejemplos.ejemplo02mapeo.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "film", indexes = {
        @Index(name = "idx_title", columnList = "title"),
        @Index(name = "idx_fk_language_id", columnList = "language_id"),
        @Index(name = "idx_fk_original_language_id", columnList = "original_language_id")
}, uniqueConstraints = {@UniqueConstraint( columnNames = {"title", "release_year"})}

)
public class Film {
    @Id
    @Column(name = "film_id", nullable = false)
    private Short id;

    @Column(name = "title", nullable = false, length = 128)
    private String title;

    @OneToOne(fetch = FetchType.LAZY)
    private FilmDetails filmDetails;

    public Short getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public FilmDetails getFilmDetails() {
        return filmDetails;
    }
}