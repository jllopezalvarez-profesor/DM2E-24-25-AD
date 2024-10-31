package es.jllopezalvarez.accesodatos.ejemplos.ejemplossax.orders;

public class OrderItem {
    private String partNumber;
    private int quantity;
    private String name;
    private double price;

    public OrderItem() {
    }

    public OrderItem(String partNumber, int quantity, String name, double price) {
        this.partNumber = partNumber;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
