package es.jllopezalvarez.accesodatos.ejemplos.ejemplosdom.modificacion;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class Ejemplo01ModificarXmlDomFichero {
    private static final Path ORDERS_PATH = Path.of("xml-files", "orders.xml");

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        // Obtenemos el objeto DocumentBuilderFactory y creamos con él un objeto DocumentBuilder.
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        // Usamos el método parse para leer el fichero XML.
        Document xmlDocument = builder.parse(ORDERS_PATH.toFile());

        // Normalizamos el documento para que no se altere por espacios o saltos de línea en los nodos
        xmlDocument.getDocumentElement().normalize();

        // Ahora podemos hacer operaciones de búsqueda.
        // Por ejemplo, obtener los nodos de todas las plantas del catálogo.
        NodeList nodeList = xmlDocument.getElementsByTagName("PurchaseOrder");

        // Vamos a modificar el XML:
        // - Cambiando los códigos de pedido, añadiendo "PO-" por delante.
        // - Añadiendo un nodo "ModifiedDate" con la fecha actual (LocalDateTime.now) en formato cadena

        // Recorremos todos los elementos
        for (int i=0; i<nodeList.getLength();i++){
            Node purchaseOrder = nodeList.item(i);
            // Obtenemos el nodo del atributo. Hay que hacer cast a Element
            Element purchaseOrderElement = (Element) purchaseOrder;
            String orderNumber = purchaseOrderElement.getAttribute("PurchaseOrderNumber");
            orderNumber = "PO-" + orderNumber;
            purchaseOrderElement.setAttribute("PurchaseOrderNumber", orderNumber);
            purchaseOrderElement.setAttribute("NuevoAtributo", "Valor");
            String modifiedDate = LocalDateTime.now().toString();
            Element modifiedDateElement = xmlDocument.createElement("ModifiedDate");
            modifiedDateElement.setTextContent(modifiedDate);
            purchaseOrderElement.appendChild(modifiedDateElement);
        }

        // Guardamos el XML modificado. Por simplicidad lo vamos a volcar en la consola.
        // Usamos un método para hacerlo
        dumpToConsole(xmlDocument);
    }

    private static void dumpToConsole(Document newDoc) throws TransformerException {
        TransformerFactory tranFactory = TransformerFactory.newInstance();
        Transformer aTransformer = tranFactory.newTransformer();
        Source src = new DOMSource(newDoc);
        Result dest = new StreamResult(System.out);
        aTransformer.transform(src, dest);
    }
}
