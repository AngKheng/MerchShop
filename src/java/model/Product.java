package model;

public class Product {
    private int id;
    private String name;
    private double price;
    private String image;
    private String description;
    private String type; // Thêm biến type

    // Cập nhật Constructor nhận 6 tham số
    public Product(int id, String name, double price, String image, String description, String type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
        this.type = type;
    }

    // Các Getter
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getImage() { return image; }
    public String getDescription() { return description; }
    public String getType() { return type; } // Getter mới

    // Các Setter
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setImage(String image) { this.image = image; }
    public void setDescription(String description) { this.description = description; }
    public void setType(String type) { this.type = type; } // Setter mới
}