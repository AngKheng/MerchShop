package dao;

import java.sql.PreparedStatement;

public class ContactDAO extends DBContext {

    // Hàm lưu tin nhắn từ trang Contact vào Database
    public void insertContact(String name, String email, String subject, String message) {
        // created_at sẽ tự động lấy giờ hệ thống nhờ GETDATE() trong SQL
        String sql = "INSERT INTO contacts (name, email, subject, message) VALUES (?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, subject);
            ps.setString(4, message);
            
            ps.executeUpdate();
            System.out.println("-> Đã lưu tin nhắn Contact thành công!");
        } catch (Exception e) {
            System.out.println("Lỗi lưu Contact: " + e.getMessage());
        }
    }
}