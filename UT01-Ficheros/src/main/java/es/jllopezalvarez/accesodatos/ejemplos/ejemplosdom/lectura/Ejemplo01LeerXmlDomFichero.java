package es.jllopezalvarez.accesodatos.ejemplos.ejemplosdom.lectura;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public class Ejemplo01LeerXmlDomFichero {
    private static final Path PLANTS_CATALOG_PATH = Path.of("xml-files", "plants-catalog.xml");

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        // Obtenemos el objeto DocumentBuilderFactory y creamos con él un objeto DocumentBuilder.
        // Esto puede lanzar checked exceptions, que estamos delegando (throws)
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        // Usamos el método parse para leer el fichero XML.
        // Usamos la sobrecarga que recibe un File, así no tenemos que abrir streams

        Document xmlDocument = builder.parse(PLANTS_CATALOG_PATH.toFile());
        // Normalizamos el documento para que no se altere por espacios o saltos de línea en los nodos
        // Es para evitar que los nodos con texto partido en líneas sean interpretados como nodos diferentes
        // No es obligatorio, pero está recomendado
        xmlDocument.getDocumentElement().normalize();

        // Ahora podemos hacer operaciones de búsqueda.
        // Por ejemplo, obtener los nodos de todas las plantas del catálogo.
        NodeList nodeList = xmlDocument.getElementsByTagName("common");
//        NodeList nodeList = xmlDocument.getElementsByTagName("plant");

        // Comprobamos si se ha encontrado algo
        int foundCount = nodeList.getLength();
        System.out.printf("Nº de elementos encontrados: %s\n", foundCount);

        // Podemos iterar los distintos elementos encontrados.
        // Como no implementa Iterable, tenemos que hacerlo por índice
        for (int i=0; i<nodeList.getLength();i++){
            Node node = nodeList.item(i);
            // Para acceder al texto del nodo usamos getTextContent();
            System.out.printf("Nombre común: %s\n", node.getTextContent());
        }
    }
}
