package es.jllopezalvarez.ejemplos.orm.hibernate.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "film")
public class Film {
    @Id
    @Column(name = "film_id", nullable = false)
    @ToString.Include
    private Short filmId;

    @Column(name = "title", nullable = false, length = 128)
    @ToString.Include
    private String title;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private Integer releaseYear;

    @ColumnDefault("3")
    @Column(name = "rental_duration", nullable = false)
    private Byte rentalDuration;

    @ColumnDefault("4.99")
    @Column(name = "rental_rate", nullable = false, precision = 4, scale = 2)
    private BigDecimal rentalRate;

    @Column(name = "length")
    private Short length;

    @ColumnDefault("19.99")
    @Column(name = "replacement_cost", nullable = false, precision = 5, scale = 2)
    private BigDecimal replacementCost;

    @ColumnDefault("'G'")
    @Lob
    @Column(name = "rating")
    private String rating;

    @Lob
    @Column(name = "special_features")
    private String specialFeatures;

    @ColumnDefault("current_timestamp()")
    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

//    @ManyToMany(mappedBy = "films")
//    private Collection<Category> categories;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "original_language_id")
    private Language originalLanguage;

    @ManyToMany(mappedBy = "films")
    private Collection<Actor> actors;

    @ManyToMany(mappedBy = "films")
    private Collection<Category> categories;
}