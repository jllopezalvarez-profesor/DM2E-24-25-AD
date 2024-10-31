package es.jllopezalvarez.accesodatos.ejemplos.ejemplossax;


import es.jllopezalvarez.accesodatos.ejemplos.ejemplossax.handler.BooksCatalogHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public class Ejemplo02LeerXmlSaxRecursos {

    private static final Path BOOKS_CATALOG_RESOURCE_PATH = Path.of("xml-resources", "books-catalog.xml");
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        // Obtenemos el objeto SAXParserFactory y creamos con él un objeto SAXParser.
        // Esto puede lanzar checked exceptions, que estamos delegando (throws)
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        // Creamos una instancia del Handler que creamos para manejar el fichero XML
        BooksCatalogHandler xmlHandler = new BooksCatalogHandler();

        // Abrimos un stream al fichero XML.
        try (InputStream xmlInputStream = loadFileFromResources(BOOKS_CATALOG_RESOURCE_PATH.toString())) {

            // Usamos el método parse para leer el fichero XML.
            // Usamos el handler que hemos creado para procesar el fichero.
            parser.parse(xmlInputStream, xmlHandler);

            // Una vez procesado, pedimos al handler que nos de los datos procesados.
            String textoGenerado = xmlHandler.getText();

            // Lo mostramos por pantalla
            System.out.println(textoGenerado);
        }
    }

    // Método para obtener un Stream que permite leer un fichero que se ha guardado
    // en el proyecto como recurso. Como los InputStream son AutoCloseable, habrá
    // que usar try-with-resources al llamar a este método.
    private static InputStream loadFileFromResources(String fileResourcePath) {
        // Obtenemos yn objeto ClassLoader, que permite, entre otras cosas, acceder a recursos.
        // Ya sabréis de PSP qué hace Thread.currentThread().
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        // Obtenemos acceso al recurso como un Stream
        return classloader.getResourceAsStream(fileResourcePath);
    }
}
