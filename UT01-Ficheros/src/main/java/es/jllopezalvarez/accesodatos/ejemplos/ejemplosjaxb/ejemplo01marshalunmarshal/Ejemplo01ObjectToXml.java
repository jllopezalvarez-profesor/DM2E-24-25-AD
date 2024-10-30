package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjaxb.ejemplo01marshalunmarshal;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.util.List;

public class Ejemplo01ObjectToXml {
    public static void main(String[] args) throws JAXBException {
        // Utilizamos la clase de utilidad para crear una colección de objetos "Person"
        List<Person> people = PersonUtils.createRandomPeople(3);

        // Creamos el contexto de JAXB
        // JAXB lanza checked exceptions, que en este caso estamos delegando (throws).
        JAXBContext context = JAXBContext.newInstance(Person.class);
        // Creamos el marshaller, el "conversor"
        // Lo configuramos para que genere XML formateado
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        // Generamos el XML de la primera persona de la lista
        marshaller.marshal(people.get(0), System.out);
        // Si intentamos hacerlo con toda la lista, falla.
        marshaller.marshal(people, System.out);

    }
}
