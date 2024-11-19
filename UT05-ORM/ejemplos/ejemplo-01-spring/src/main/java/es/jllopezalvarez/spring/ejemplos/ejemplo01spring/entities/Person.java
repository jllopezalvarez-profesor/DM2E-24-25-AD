package es.jllopezalvarez.spring.ejemplos.ejemplo01spring.entities;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Person {
    @EqualsAndHashCode.Include
    private final int personId;
    private final String firstName;
    private final String lastName;
}
