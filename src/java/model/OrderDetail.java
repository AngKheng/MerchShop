package model;

public class OrderDetail {
    private int productId; // Thêm ID để máy tính biết trừ kho món nào
    private String productName;
    private String productImage;
    private int quantity;
    private double price;

    // Constructor đầy đủ để hiển thị lên giao diện Admin/Hóa đơn
    public OrderDetail(int productId, String productName, String productImage, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.productImage = productImage;
        this.quantity = quantity;
        this.price = price;
    }

    // --- Getter & Setter ---
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public String getProductImage() { return productImage; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
}