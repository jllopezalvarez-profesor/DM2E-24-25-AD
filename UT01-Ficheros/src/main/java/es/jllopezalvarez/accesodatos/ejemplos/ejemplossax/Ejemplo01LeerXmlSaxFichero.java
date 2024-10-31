package es.jllopezalvarez.accesodatos.ejemplos.ejemplossax;



import es.jllopezalvarez.accesodatos.ejemplos.ejemplossax.handler.PlantsCatalogXmlHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.file.Path;

public class Ejemplo01LeerXmlSaxFichero {
    private static final Path PLANTS_CATALOG_PATH = Path.of("xml-files", "plants-catalog.xml");

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        // Obtenemos el objeto SAXParserFactory y creamos con él un objeto SAXParser.
        // Esto puede lanzar checked exceptions, que estamos delegando (throws)
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        // Creamos una instancia del Handler que creamos para manejar el fichero XML
        PlantsCatalogXmlHandler xmlHandler = new PlantsCatalogXmlHandler();

        // Usamos el método parse para leer el fichero XML.
        // Usamos el handler que hemos creado para procesar el fichero.
        parser.parse(PLANTS_CATALOG_PATH.toFile(), xmlHandler);

        // Una vez procesado, pedimos al handler que nos de los datos procesados.
        String textoGenerado = xmlHandler.getText();

        // Lo mostramos por pantalla
        System.out.println(textoGenerado);
    }
}
