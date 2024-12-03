package es.jllopezalvarez.ejemplos.orm.hibernate.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "language")
public class Language {
    @Id
    @Column(name = "language_id", nullable = false)
    @ToString.Include
    private Byte languageId;

    @Column(name = "name", nullable = false, length = 20)
    @ToString.Include
    private String name;

    @ColumnDefault("current_timestamp()")
    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

}