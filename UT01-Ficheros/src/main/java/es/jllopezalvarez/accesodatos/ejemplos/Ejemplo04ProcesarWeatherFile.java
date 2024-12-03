package es.jllopezalvarez.accesodatos.ejemplos;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplossax.handler.WeatherHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.file.Path;

public class Ejemplo04ProcesarWeatherFile {
    private static final Path PATH_FICHERO = Path.of("xml-files", "weather-data.xml");
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        // Pedir al usuario ficheros y comprobar si existen o no, pedir confirmación, etc.

        // Crear el SaxParserFactory
        SAXParserFactory factory = SAXParserFactory.newInstance();

        // Crear el SaxParser
        SAXParser saxParser = factory.newSAXParser();

        // Crear el handler (procesa el fichero)
        WeatherHandler weatherHandler = new WeatherHandler();

        // Leer el fichero con el parser y pasar los datos al handler
        saxParser.parse( PATH_FICHERO.toFile(), weatherHandler);

        // Obtener resultados del handler


    }
}
