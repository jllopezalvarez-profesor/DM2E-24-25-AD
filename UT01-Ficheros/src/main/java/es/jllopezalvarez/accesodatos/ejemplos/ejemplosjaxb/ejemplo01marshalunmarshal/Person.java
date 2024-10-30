package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjaxb.ejemplo01marshalunmarshal;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "persona")
public class Person {
    //@XmlAttribute(name = "identificador")
    private String id;
    //@XmlTransient
    private String firstName;
    private String lastName;

    public Person(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // JAXB necesita un constructor vacío para poder instanciar objetos
    // El constructor puede ser privado, no es necesario que sea público
    private Person() {
    }

    @XmlAttribute(name = "identificador")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //@XmlAttribute
    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
