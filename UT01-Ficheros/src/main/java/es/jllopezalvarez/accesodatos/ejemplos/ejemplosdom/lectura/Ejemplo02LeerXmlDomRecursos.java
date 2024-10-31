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

public class Ejemplo02LeerXmlDomRecursos {

    private static final Path BOOKS_CATALOG_RESOURCE_PATH = Path.of("xml-resources", "books-catalog.xml");

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        // Obtenemos el objeto DocumentBuilderFactory y creamos con él un objeto DocumentBuilder.
        // Esto puede lanzar checked exceptions, que estamos delegando (throws)
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        // Abrimos un stream al fichero XML.
        try (InputStream xmlInputStream = loadFileFromResources(BOOKS_CATALOG_RESOURCE_PATH.toString())) {
            // Parseamos el XML y creamos el documento
            Document xmlDocument = builder.parse(xmlInputStream);
            // Normalizamos el documento para que no se altere por espacios o saltos de línea en los nodos
            xmlDocument.getDocumentElement().normalize();

            // Ahora podemos hacer operaciones de búsqueda. Por ejemplo
            NodeList nodeList = xmlDocument.getElementsByTagName("author");


            // Comprobamos si se ha encontrado algo
            int foundCount = nodeList.getLength();
            System.out.printf("Nº de elementos encontrados: %s\n", foundCount);

            // Podemos iterar los distintos elementos encontrados.
            // Como no implementa Iterable, tenemos que hacerlo por índice
            // Ojo, esto no elimina duplicados
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                // Para acceder al texto del nodo usamos getTextContent();
                System.out.printf("Autor: %s\n", node.getTextContent(), node.getNodeValue());
            }
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
