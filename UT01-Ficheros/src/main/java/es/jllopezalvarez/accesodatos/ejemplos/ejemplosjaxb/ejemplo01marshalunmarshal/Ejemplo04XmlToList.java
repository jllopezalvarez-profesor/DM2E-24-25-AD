package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjaxb.ejemplo01marshalunmarshal;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.StringReader;

public class Ejemplo04XmlToList {

    private static final String PEOPLE_XML = """
            <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                     <people>
                         <person identificador="275-52-3622">
                             <firstName>Natisha</firstName>
                             <lastName>Jaskolski</lastName>
                         </person>
                         <person identificador="322-22-5641">
                             <firstName>Morton</firstName>
                             <lastName>Hudson</lastName>
                         </person>
                         <person identificador="059-58-4167">
                             <firstName>Chance</firstName>
                             <lastName>Tremblay</lastName>
                         </person>
                     </people>
            """;

    public static void main(String[] args) throws JAXBException {
        // Creamos un Stream para leer la cadena con el XML
        try (StringReader reader = new StringReader(PEOPLE_XML)) {
            // Creamos el contexto JAXB y el unmarshaller
            JAXBContext context = JAXBContext.newInstance(People.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            // Hacemos la conversión de la lista de personas
            // Hay que hacer cast, al igual que en una
            // lectura de objetos con ObjectInputStream
            People people = (People) unmarshaller.unmarshal(reader);

            System.out.println(people);


        }

    }
}
