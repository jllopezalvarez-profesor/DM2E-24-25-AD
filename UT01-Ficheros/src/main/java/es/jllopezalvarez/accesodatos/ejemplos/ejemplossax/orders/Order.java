package es.jllopezalvarez.accesodatos.ejemplos.ejemplossax.orders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderNumber;
    private LocalDate orderDate;
    private String deliveryNotes;
    private List<OrderItem> items;


    public Order() {
        this.items = new ArrayList<>();
    }

    public Order(int orderNumber, LocalDate orderDate, String deliveryNotes) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.deliveryNotes = deliveryNotes;
        this.items = new ArrayList<>();
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryNotes() {
        return deliveryNotes;
    }

    public void setDeliveryNotes(String deliveryNotes) {
        this.deliveryNotes = deliveryNotes;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void addItem(OrderItem item) {
        this.items.add(item);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Pedido ").append(orderNumber).append(" ").append(orderDate).append("\n");
        sb.append("Observaciones de entrega: ").append(deliveryNotes).append("\n");
        sb.append("Productos:").append("\n");
        for (OrderItem item : items) {
            sb.append(String.format("\t%s - %s - %s$ - %s unidades\n", item.getPartNumber(), item.getName(), item.getPrice(), item.getQuantity()));
        }
        return sb.toString();
    }
}
