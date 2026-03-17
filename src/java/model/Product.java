package model;

public class Product {
    private int id;
    private String name;
    private double price;
    private String image;
    private String description; // Thêm trường mô tả

    // Cập nhật Constructor để nhận đủ 5 tham số
    public Product(int id, String name, double price, String image, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
    }

    // --- Các Getter ---
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getImage() { return image; }
    public String getDescription() { return description; }

    // --- Các Setter ---
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setImage(String image) { this.image = image; }
    public void setDescription(String description) { this.description = description; }
}