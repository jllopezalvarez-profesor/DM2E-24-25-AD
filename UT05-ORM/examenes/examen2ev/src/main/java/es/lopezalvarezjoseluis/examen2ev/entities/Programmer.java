package es.lopezalvarezjoseluis.examen2ev.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Programmer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    @Column(nullable = false, length = 200)
    private String name;
    @Column(nullable = false)
    private Double minIncome;
    @Column(nullable = false)
    private Double maxIncome;
}
