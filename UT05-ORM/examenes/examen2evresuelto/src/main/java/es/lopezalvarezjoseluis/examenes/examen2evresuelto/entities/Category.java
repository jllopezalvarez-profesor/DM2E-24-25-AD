package es.lopezalvarezjoseluis.examenes.examen2evresuelto.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
@Getter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private Double minIncome;

    @Column(nullable = false)
    private Double maxIncome;

    public String getIncomeRange (){
        return String.format("De %.2f a %.2f", minIncome , maxIncome);
    }
}
