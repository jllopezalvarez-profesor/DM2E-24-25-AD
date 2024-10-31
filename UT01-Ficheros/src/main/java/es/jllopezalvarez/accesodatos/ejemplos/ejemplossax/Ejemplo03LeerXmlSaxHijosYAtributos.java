package es.jllopezalvarez.accesodatos.ejemplos.ejemplossax;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplossax.handler.OrderHandler;
import es.jllopezalvarez.accesodatos.ejemplos.ejemplossax.orders.Order;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/*
 * Ejemplo de lectura de un XML para listar todos los pedidos (nodos PurchaseOrder). De cada pedido mostraremos su
 * número de pedido y fecha (atributos PurchaseOrderNumber y OrderDate). Además, la lista de artículos (nodos Item)
 * que incluye. Para cada artículo, mostramos su código (atributo PartNumber),
 * el nombre del artículo (nodo ProductName) y cantidad (nodo Quantity).
 */
public class Ejemplo03LeerXmlSaxHijosYAtributos {
    private static final Path ORDERS_PATH = Path.of("xml-files", "orders.xml");

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        // Obtenemos el objeto SAXParserFactory y creamos con él un objeto SaxParser.
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        // Creamos el handler
        OrderHandler orderHandler = new OrderHandler();

        // Usamos el método parse para leer el fichero XML.
        parser.parse(ORDERS_PATH.toFile(), orderHandler);

        // Obtenemos los resultados
        List<Order> orders = orderHandler.getGeneratedOrders();

        // Mostramos la lista
        printOrders(orders);
    }

    private static void printOrders(Iterable<Order> orders) {
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}
