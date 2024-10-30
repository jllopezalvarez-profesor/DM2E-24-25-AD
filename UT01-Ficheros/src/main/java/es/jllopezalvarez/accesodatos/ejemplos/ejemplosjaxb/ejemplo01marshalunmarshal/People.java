package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjaxb.ejemplo01marshalunmarshal;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase es un "wrapper" para poder generar XML de colecciones de personas
 */
@XmlRootElement
// Usamos XmlAccessType.FIELD para que mapee el atributo privado "people"
// Alternativamente, podríamos haber puesto un getter, que se mapea con
// el valor por defecto, XmlAccessorType.PUBLIC_MEMBER
@XmlAccessorType(XmlAccessType.FIELD)
public class People {
    // Configuramos el nombre de los nodos XML de la colección

    @XmlElement(name = "person")
    private List<Person> people;

    public People(List<Person> people) {
        this.people = people;
    }

    // El constructor privado para poder usar JAXB
    private People() {
        this.people = new ArrayList<>();
    }

    @Override
    public String toString() {
        return people.toString();
    }
}
