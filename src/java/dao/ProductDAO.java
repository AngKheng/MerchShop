package dao;

import model.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// Kế thừa DBContext để có sẵn biến 'connection'
public class ProductDAO extends DBContext {

    // 1. Hàm lấy toàn bộ sản phẩm từ SQL Server để in ra trang chủ
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products ORDER BY id DESC"; // Lấy sp mới nhất lên đầu
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                list.add(new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getString("image"),
                    rs.getString("description")
                ));
            }
        } catch (Exception e) {
            System.out.println("Lỗi lấy danh sách sản phẩm: " + e.getMessage());
        }
        return list;
    }

    // 2. Hàm thêm sản phẩm mới vào SQL Server (sau khi có link Cloudinary)
    public void insertProduct(String name, double price, String image, String description) {
        String sql = "INSERT INTO products (name, price, image, description) VALUES (?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setString(3, image);
            ps.setString(4, description);
            
            ps.executeUpdate(); // Chạy lệnh Insert
            System.out.println("Đã lưu sản phẩm vào Database thành công!");
        } catch (Exception e) {
            System.out.println("Lỗi lưu sản phẩm: " + e.getMessage());
        }
    }
    // 3. Hàm lấy 1 sản phẩm dựa vào ID (Dùng cho Trang chi tiết và Thêm Giỏ hàng)
    public Product getProductById(int id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id); // Truyền số ID người dùng click vào dấu hỏi chấm
            
            ResultSet rs = ps.executeQuery();
            
            // Nếu tìm thấy dòng dữ liệu phù hợp
            if (rs.next()) {
                return new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getString("image"),
                    rs.getString("description")
                );
            }
        } catch (Exception e) {
            System.out.println("Lỗi lấy sản phẩm theo ID: " + e.getMessage());
        }
        return null; // Trả về null nếu không tìm thấy
    }
}