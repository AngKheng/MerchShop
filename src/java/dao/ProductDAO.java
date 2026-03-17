package dao;

import model.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// Kế thừa DBContext để có sẵn biến 'connection'
public class ProductDAO extends DBContext {

// 1. Cập nhật hàm getAllProducts
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products ORDER BY id DESC"; 
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(
                    rs.getInt("id"), rs.getString("name"), rs.getDouble("price"),
                    rs.getString("image"), rs.getString("description"), 
                    rs.getString("type"), rs.getInt("quantity"),rs.getDouble("sale_price"), 
    rs.getBoolean("is_sold_out")
                ));
            }
        } catch (Exception e) { System.out.println("Lỗi getAllProducts: " + e.getMessage()); }
        return list;
    }

    // 2. Cập nhật hàm getProductById
    public Product getProductById(int id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Product(
                    rs.getInt("id"), rs.getString("name"), rs.getDouble("price"),
                    rs.getString("image"), rs.getString("description"), 
                    rs.getString("type"),  rs.getInt("quantity"),rs.getDouble("sale_price"), 
    rs.getBoolean("is_sold_out") // Đọc thêm cột type
                );
            }
        } catch (Exception e) { System.out.println("Lỗi getProductById: " + e.getMessage()); }
        return null;
    }

    // 3. Cập nhật hàm insertProduct để ghi thêm cột type
// 1. Sửa tham số truyền vào (Thêm int quantity)
    public void insertProduct(String name, double price, String image, String description, String type, int quantity) {
        
        // 2. Sửa câu SQL (Thêm chữ quantity và 1 dấu ? nữa thành 6 dấu hỏi)
        String sql = "INSERT INTO products (name, price, image, description, type, quantity) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setString(3, image);
            ps.setString(4, description);
            ps.setString(5, type);
            // 3. Truyền giá trị cho dấu hỏi số 6
            ps.setInt(6, quantity); 
            
            ps.executeUpdate();
        } catch (Exception e) { 
            System.out.println("Lỗi insertProduct: " + e.getMessage()); 
        }
    }
    // 4. Hàm lọc sản phẩm theo Danh mục (Type)
    public List<Product> getProductsByType(String type) {
        List<Product> list = new ArrayList<>();
        // Tìm những sản phẩm có type khớp với chữ truyền vào
        String sql = "SELECT * FROM products WHERE type = ? ORDER BY id DESC"; 
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, type); // Lắp chữ (VD: 'FFXIV') vào dấu hỏi chấm
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                list.add(new Product(
                    rs.getInt("id"), rs.getString("name"), rs.getDouble("price"),
                    rs.getString("image"), rs.getString("description"), 
                    rs.getString("type"), rs.getInt("quantity"), rs.getDouble("sale_price"), 
    rs.getBoolean("is_sold_out")
                ));
            }
        } catch (Exception e) { 
            System.out.println("Lỗi getProductsByType: " + e.getMessage()); 
        }
        return list;
    }
    // 5. Hàm Xóa sản phẩm theo ID
    public void deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id); // Lắp ID cần xóa vào dấu hỏi chấm
            ps.executeUpdate();
            System.out.println("-> Đã xóa thành công sản phẩm ID: " + id);
        } catch (Exception e) {
            System.out.println("Lỗi deleteProduct: " + e.getMessage());
        }
    }
    // 6. Hàm Cập nhật (Sửa) sản phẩm
    public void updateProduct(int id, String name, double price, String image, String description, String type, int quantity, double salePrice, boolean isSoldOut) {
        String sql = "UPDATE products SET name = ?, price = ?, image = ?, description = ?, type = ?, quantity = ?, sale_price = ?, is_sold_out = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setString(3, image);
            ps.setString(4, description);
            ps.setString(5, type);
            ps.setInt(6, quantity);
            ps.setDouble(7, salePrice);
            ps.setBoolean(8, isSoldOut);
            ps.setInt(9, id); // id nằm ở cuối cùng theo đúng thứ tự câu lệnh SQL
            ps.executeUpdate();
            System.out.println("-> Đã cập nhật thành công sản phẩm ID: " + id);
        } catch (Exception e) {
            System.out.println("Lỗi updateProduct: " + e.getMessage());
        }
    }
    // 7. Hàm tìm kiếm sản phẩm theo tên
    public List<Product> searchProductsByName(String keyword) {
        List<Product> list = new ArrayList<>();
        // Lệnh LIKE %...% giúp tìm kiếm gần đúng. VD: gõ "kan" sẽ ra "Kanade"
        String sql = "SELECT * FROM products WHERE name LIKE ? ORDER BY id DESC"; 
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%"); 
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                // Nhớ đúc khuôn với đủ 9 nguyên liệu nhé
                list.add(new Product(
                    rs.getInt("id"), rs.getString("name"), rs.getDouble("price"),
                    rs.getString("image"), rs.getString("description"), 
                    rs.getString("type"), rs.getInt("quantity"), 
                    rs.getDouble("sale_price"), rs.getBoolean("is_sold_out")
                ));
            }
        } catch (Exception e) { 
            System.out.println("Lỗi searchProductsByName: " + e.getMessage()); 
        }
        return list;
    }
}