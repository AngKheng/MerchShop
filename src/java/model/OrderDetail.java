package model;

public class OrderDetail {
    private String productName;
    private String productImage;
    private int quantity;
    private double price;

    public OrderDetail(String productName, String productImage, int quantity, double price) {
        this.productName = productName;
        this.productImage = productImage;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductName() { return productName; }
    public String getProductImage() { return productImage; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
}