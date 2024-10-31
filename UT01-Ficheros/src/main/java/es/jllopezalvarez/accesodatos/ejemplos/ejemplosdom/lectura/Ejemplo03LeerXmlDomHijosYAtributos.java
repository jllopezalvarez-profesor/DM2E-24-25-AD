package es.jllopezalvarez.accesodatos.ejemplos.ejemplosdom.lectura;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;

/*
 * Ejemplo de lectura de un XML para listar todos los pedidos (nodos PurchaseOrder). De cada pedido mostraremos su
 * número de pedido y fecha (atributos PurchaseOrderNumber y OrderDate). Además, la lista de artículos (nodos Item)
 * que incluye. Para cada artículo, mostramos su código (atributo PartNumber),
 * el nombre del artículo (nodo ProductName) y cantidad (nodo Quantity).
 */
public class Ejemplo03LeerXmlDomHijosYAtributos {
    private static final Path ORDERS_PATH = Path.of("xml-files", "orders.xml");

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        // Obtenemos el objeto DocumentBuilderFactory y creamos con él un objeto DocumentBuilder.
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        // Usamos el método parse para leer el fichero XML.
        Document xmlDocument = builder.parse(ORDERS_PATH.toFile());

        // Normalizamos el documento
        xmlDocument.getDocumentElement().normalize();

        // Obtenemos todos los nodos de pedido (PurchaseOrder)
        NodeList purchaseOrders = xmlDocument.getElementsByTagName("PurchaseOrder");

        printOrders(purchaseOrders);
    }

    /**
     * Itera una serie de pedidos y los muestra en la consola
     *
     * @param purchaseOrders El pedido a mostrar
     */
    private static void printOrders(NodeList purchaseOrders) {
        for (int i = 0; i < purchaseOrders.getLength(); i++) {
            Node purchaseOrder = purchaseOrders.item(i);
            printOrder(purchaseOrder);
        }
    }

    /**
     * Muestra un pedido en la consola
     *
     * @param purchaseOrder El pedido a mostrar
     */
    private static void printOrder(Node purchaseOrder) {
        // Obtenemos el número de pedido y la fecha
        String orderNumber = purchaseOrder.getAttributes().getNamedItem("PurchaseOrderNumber").getTextContent();
        String orderDate = purchaseOrder.getAttributes().getNamedItem("OrderDate").getTextContent();
        // Alternativa, hacer cast a Element y usar "getAttribute"
        orderNumber = ((Element)purchaseOrder).getAttribute("PurchaseOrderNumber");
        orderDate = ((Element)purchaseOrder).getAttribute("OrderDate");



        // Mostramos los datos del pedido
        System.out.printf("Pedido %s - %s\n", orderNumber, orderDate);

        // Obtenemos los nodos "Item" que son descencientes del nodo del pedido.
        // Como queremos usar el método getElementsByTagName, hay que hacer un upcastcast a Element
        // Esto podría fallar si el nodo purchaseOrder no fuera un elemento.
        Element purchaseOrderElement = (Element) purchaseOrder;
        NodeList items = purchaseOrderElement.getElementsByTagName("Item");
        // Mostramos los datos de los artículos. Usamos
        printOrderItems(items);
    }

    // Itera una serie de elemenots
    private static void printOrderItems(NodeList orderItems) {
        for (int i = 0; i < orderItems.getLength(); i++) {
            Node orderItem = orderItems.item(i);
            printOrderItem(orderItem);
        }

    }

    private static void printOrderItem(Node orderItem) {
        // Obtenemos el id de producto, el nombre y la cantidad
        // Otra vez, hacemos cast a Element para poder obtener nodos hijos.
        String productNumber = ((Element)orderItem).getAttribute ("PartNumber");
        Element orderItemElement = (Element) orderItem;
        String productName = orderItemElement.getElementsByTagName("ProductName").item(0).getTextContent();
        String quantity = orderItemElement.getElementsByTagName("Quantity").item(0).getTextContent();

        System.out.printf("\t%s - %s - %s\n", productNumber, productName, quantity);


    }
}
