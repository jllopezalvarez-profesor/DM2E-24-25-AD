package es.jllopezalvarez.accesodatos.ejemplos.ejemplossax.handler;


import es.jllopezalvarez.accesodatos.ejemplos.ejemplossax.orders.Order;
import es.jllopezalvarez.accesodatos.ejemplos.ejemplossax.orders.OrderItem;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderHandler extends DefaultHandler {
    // ArrayList para almacenar todos los pedidos que se lean del fichero. Se crea en la declaración, vacío.
    // Como alternativa podría no inicializarse (valdría null) e inicializarlo como respuesta al inicio del documento.
    private List<Order> orders = new ArrayList<>();

    // Objeto de la clase Order para guardar los datos de un pedido mientras se va procesando.
    // No se inicializa porque se creará en un método para el evento de inicio de elemento.
    private Order order;

    // Objeto de la clase OrderItem para almacenar los datos de un producto de pedido mientras se va procesando.
    // No se inicializa porque se creará en un método para el evento de inicio de elemento.
    private OrderItem orderItem;

    // StringBuilder para ir almacenando el texto que haya dentro de los elementos XML.
    StringBuilder text = new StringBuilder();

    // Variables boolean para saber si estamos en un elemento o en otro.
    boolean inOrder = false;
    boolean inDeliveryNotes = false;
    boolean inOrderItem = false;
    boolean inProductName = false;
    boolean inQuantity = false;
    boolean inUsPrice = false;

    // Evento para procesar los inicios de elemento.
    // Se procesan varios tipos de elementos:
    // Para el pedido: PurchaseOrder, DeliveryNotes
    // Para el item del pedido: Item, ProductName, Quantity, USPrice
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // A minúsculas para comparar. Podría usar equalsIgnoreCase como alternativa, pero así usamos switch
        qName = qName.toLowerCase();
        switch (qName) {
            case "purchaseorder":
                // Inicio de pedido. Creamos un objeto y extraemos datos de atributos.
                order = new Order();
                int orderNumber = Integer.parseInt(attributes.getValue("PurchaseOrderNumber"));
                order.setOrderNumber(orderNumber);
                LocalDate orderDate = LocalDate.parse(attributes.getValue("OrderDate"));
                order.setOrderDate(orderDate);
                // Establecemos la variable bool en true
                inOrder = true;
                break;
            case "deliverynotes":
                // Inicio de elemento con notas de entrega
                // Inicializamos el string builder para texto
                text.setLength(0); // Más rápido que hacer new StringBuilder
                inDeliveryNotes = true;
                break;
            case "item":
                // Inicio de item de pedido. Creamos un objeto y extraemos datos de atributos.
                orderItem = new OrderItem();
                String partNumber = attributes.getValue("PartNumber");
                orderItem.setPartNumber(partNumber);
                // Establecemos la variable bool en true
                inOrderItem = true;
                break;
            case "productname":
                // Inicializamos el string builder para texto
                text.setLength(0); // Más rápido que hacer new StringBuilder
                inProductName = true;
                break;
            case "quantity":
                // Inicializamos el string builder para texto
                text.setLength(0); // Más rápido que hacer new StringBuilder
                inQuantity = true;
                break;
            case "usprice":
                // Inicializamos el string builder para texto
                text.setLength(0); // Más rápido que hacer new StringBuilder
                inUsPrice = true;
                break;
            // No hay default. El resto de elementos no los procesamos.
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // Si estamos en cualquier nodo que debamos recoger texto, lo guardamos
        // el resto de nodos no se hace nada
        if (inDeliveryNotes || inProductName || inQuantity || inUsPrice) {
            text.append(ch, start, length);
        }
    }

    // Evento para procesar los finales de elemento.
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // A minúsculas para comparar. Podría usar equalsIgnoreCase como alternativa, pero así usamos switch
        qName = qName.toLowerCase();
        switch (qName) {
            case "purchaseorder":
                // Fin de pedido. Lo añadimos a la lista de pedidos
                orders.add(order);
                // Establecemos la variable bool con false
                inOrder = false;
                break;
            case "deliverynotes":
                // Fin de notas de entrega. Tomamos el texto y lo guardamos en el pedido
                order.setDeliveryNotes(text.toString());
                inDeliveryNotes = false;
                break;
            case "item":
                // Fin de item de pedido. Añadimos el item a la lista de elementos del pedido.
                order.addItem(orderItem);
                inOrderItem = false;
                break;
            case "productname":
                // Fin de nombre de producto. Tomamos el texto y lo guardamos en el item
                orderItem.setName(text.toString());
                inProductName = false;
                break;
            case "quantity":
                // Fin de cantidad de producto. Tomamos el texto y lo guardamos en el item
                orderItem.setQuantity(Integer.parseInt(text.toString()));
                inQuantity = false;
                break;
            case "usprice":
                // Fin de precio de producto. Tomamos el texto y lo guardamos en el item
                orderItem.setPrice(Double.parseDouble(text.toString()));
                inUsPrice = false;
                break;
            // No hay default. El resto de elementos no los procesamos.
        }

    }

    public List<Order> getGeneratedOrders(){
        return this.orders;
    }
}
