package model;

public class Product {
    private int id;
    private String name;
    private double price;
    private String image;
    private String description;
    private String type;
    private int quantity; // BỔ SUNG: Số lượng tồn kho
    private double salePrice;
    private boolean isSoldOut;

public Product(int id, String name, double price, String image, String description, String type, int quantity, double salePrice, boolean isSoldOut) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
        this.type = type;
        this.quantity = quantity;
        this.salePrice = salePrice;
        this.isSoldOut = isSoldOut;
    }

    // Các Getter
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getImage() { return image; }
    public String getDescription() { return description; }
    public String getType() { return type; }
    public int getQuantity() { return quantity; }

    // Các Setter
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setImage(String image) { this.image = image; }
    public void setDescription(String description) { this.description = description; }
    public void setType(String type) { this.type = type; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    // --- BỔ SUNG GETTER VÀ SETTER CHO 2 BIẾN MỚI ---
    
    public double getSalePrice() { 
        return salePrice; 
    }
    
    public void setSalePrice(double salePrice) { 
        this.salePrice = salePrice; 
    }

    // Với biến boolean (đúng/sai), getter thường dùng chữ "is" thay vì "get"
    public boolean isSoldOut() { 
        return isSoldOut; 
    }
    
    public void setSoldOut(boolean isSoldOut) { 
        this.isSoldOut = isSoldOut; 
    }
}