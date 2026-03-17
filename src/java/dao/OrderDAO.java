package dao;

import java.sql.PreparedStatement;

public class OrderDAO extends DBContext {

    // Hàm lưu đơn hàng mới vào Database
    public void insertOrder(String name, String phone, String address, String email, double totalAmount, String status) {
        // Cột create_at thường được SQL Server tự động tạo mặc định là GETDATE()
        String sql = "INSERT INTO orders (customer_name, phone, address, email, total_amount, status) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, address);
            ps.setString(4, email);
            ps.setDouble(5, totalAmount); // Tổng tiền
            ps.setString(6, status);      // Trạng thái (VD: "Đã thanh toán VNPay")
            
            ps.executeUpdate();
            System.out.println("-> Đã lưu đơn hàng vào Database thành công!");
        } catch (Exception e) {
            System.out.println("Lỗi lưu đơn hàng: " + e.getMessage());
        }
    }
}