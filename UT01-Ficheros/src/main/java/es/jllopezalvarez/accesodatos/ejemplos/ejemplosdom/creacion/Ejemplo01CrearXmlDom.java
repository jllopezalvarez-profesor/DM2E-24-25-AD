package es.jllopezalvarez.accesodatos.ejemplos.ejemplosdom.creacion;

import net.datafaker.Faker;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Ejemplo01CrearXmlDom {

    public static final int MIN_AGE = 20;
    public static final int MAX_AGE = 40;
    public static final int HOW_MANY_PEOPLE = 30;
    public static final int INDENT = 5;

    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        // Antes de nada, vamos a crear una colección de personas aleatorias, para tener datos que escribir
        List<Person> people = createRandomPeople(HOW_MANY_PEOPLE);

        // Obtenemos el objeto DocumentBuilderFactory y creamos con él un objeto DocumentBuilder.
        // Esto puede lanzar checked exceptions, que estamos delegando (throws)
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        // Usamos el método newDocument para crear el documento XML vacío.
        Document xmlDocument = builder.newDocument();

        // Podemos fijar la versión de XML y si es standalone o no
        xmlDocument.setXmlVersion("1.0");
        xmlDocument.setXmlStandalone(true);

        // Creamos el elemento raíz del XML
        Element peopleElement = xmlDocument.createElement("people");

        // Para cada persona dentro de la colección tenemos que crear un elemento person, que tendrá:
        // - Un atributo id para el campo id
        // - Un elemento para el campo birthDate
        // - Un elemento "name" en el tendremos dos elementos "firstName" y "lastName"

        for (Person person : people) {
            // Creamos el elemento para la persona
            Element personElement = xmlDocument.createElement("person");

            // Establecemos el valor del atributo id. Esto crea el atributo sin necesidad de crear un objeto Attr
            personElement.setAttribute("id", person.getId());
            // Creamos el elemento para la fecha de nacimiento y la añadimos
            Element birthDateElement = xmlDocument.createElement("birthDate");
            birthDateElement.setTextContent(person.getBirthDate().toString());
            personElement.appendChild(birthDateElement);
            // Creamos elemento para el nombre (que contendrá dos elementos)
            Element nameElement = xmlDocument.createElement("name");
            // Creamos elemento para el nombre de pila y lo añadimos al nombre
            Element firstNameElement = xmlDocument.createElement("firstName");
            firstNameElement.setTextContent(person.getFirstName());
            nameElement.appendChild(firstNameElement);
            // Creamos elemento para el apellido y lo añadimos al nombre
            Element lastNameElement = xmlDocument.createElement("lastName");
            lastNameElement.setTextContent(person.getLastName());
            nameElement.appendChild(lastNameElement);
            // Añadimos el nombre a la persona
            personElement.appendChild(nameElement);

            // Añadimos la persona al nodo que las contiene (people)
            peopleElement.appendChild(personElement);
        }

        // Añadimos el elemento people al documento
        xmlDocument.appendChild(peopleElement);

        // Volcamos el XML a un stream. Lo hacemos en consola, pero puede ser disco, si queremos guardarlo.
        dumpToConsole(xmlDocument);
    }

    private static List<Person> createRandomPeople(int howMany) {
        Faker faker = new Faker();
        List<Person> people = new ArrayList<>(howMany);
        while (howMany > 0){
            String id = faker.idNumber().valid();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            LocalDate birthDate = faker.date().birthday(MIN_AGE, MAX_AGE).toLocalDateTime().toLocalDate();

            people.add(new Person(id, firstName, lastName, birthDate));

            howMany--;
        }
        return people;
    }

    private static void dumpToConsole(Document newDoc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        transformerFactory.setAttribute("indent-number", INDENT);
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        // Se puede omitir la declaración XML
        // transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        Source src = new DOMSource(newDoc);
        Result dest = new StreamResult(System.out);
        transformer.transform(src, dest);
    }
}




