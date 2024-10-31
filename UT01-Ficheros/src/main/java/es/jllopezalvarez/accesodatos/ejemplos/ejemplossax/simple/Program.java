package es.jllopezalvarez.accesodatos.ejemplos.ejemplossax.simple;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.file.Path;

public class Program {


    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        Path pathXml = Path.of("xml-files", "orders.xml");
        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
        parser.parse(pathXml.toFile(), new SimpleSaxHandler());


    }
}
