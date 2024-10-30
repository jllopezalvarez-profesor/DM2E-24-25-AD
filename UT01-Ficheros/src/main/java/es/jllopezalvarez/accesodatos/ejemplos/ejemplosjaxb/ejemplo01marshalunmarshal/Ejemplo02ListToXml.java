package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjaxb.ejemplo01marshalunmarshal;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.util.List;

public class Ejemplo02ListToXml {
    public static void main(String[] args) throws JAXBException {
        // Utilizamos la clase de utilidad para crear una colección de objetos "Person"
        List<Person> people = PersonUtils.createRandomPeople(3);

        // Creamos el contexto de JAXB
        // JAXB lanza checked exceptions, que en este caso estamos delegando (throws).
        // Esta vez creamos el contexto para la clase People, porque vamos a usar el wrapper
        JAXBContext context = JAXBContext.newInstance(People.class);
        // Creamos el marshaller, el "conversor"
        // Lo configuramos para que genere XML formateado
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        // Ahora no podemos generamos el XML sólo de la primera persona de la lista
        // El contexto se había creado para People, no Person
        // marshaller.marshal(people.get(0), System.out);
        // En este caso, usamos un wrapper "People", no la lista directamente
        marshaller.marshal(new People(people), System.out);

    }
}
