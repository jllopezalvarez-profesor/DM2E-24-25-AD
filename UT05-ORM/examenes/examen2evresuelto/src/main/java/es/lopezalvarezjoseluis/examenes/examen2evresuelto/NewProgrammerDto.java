package es.lopezalvarezjoseluis.examenes.examen2evresuelto;

import lombok.Getter;
import lombok.Setter;

@Getter // Si no se usa Lombok crear getter para los atributos
@Setter // Si no se usa Lombok crear setter para los atributos
public class NewProgrammerDto {
    private String documentNumber;
    private String firstName;
    private String lastName;
    private Integer categoryId;
}