package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import model.OrderDetail;

public class OrderDAO extends DBContext {

    // 1. Lưu đơn hàng chính và TRẢ VỀ ID CỦA ĐƠN ĐÓ
    public int insertOrderReturnId(String name, String phone, String address, String email, double totalAmount, String status) {
        String sql = "INSERT INTO orders (customer_name, phone, address, email, total_amount, status) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, address);
            ps.setString(4, email);
            ps.setDouble(5, totalAmount);
            ps.setString(6, status);
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); 
            }
        } catch (Exception e) {
            System.out.println("Lỗi insertOrder: " + e.getMessage());
        }
        return 0; 
    }

    // 2. Lưu từng món hàng khách đã chọn vào bảng order_details
    public void insertOrderDetail(int orderId, int productId, int quantity, double price) {
        String sql = "INSERT INTO order_details (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.setInt(2, productId);
            ps.setInt(3, quantity);
            ps.setDouble(4, price); 
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Lỗi insertOrderDetail: " + e.getMessage());
        }
    }

    // --- BỔ SUNG: HÀM CẬP NHẬT TRẠNG THÁI (Dùng cho VNPay/SePay/Admin) ---
    public boolean updateOrderStatus(int orderId, String newStatus) {
        String sql = "UPDATE orders SET status = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, newStatus);
            ps.setInt(2, orderId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi updateOrderStatus: " + e.getMessage());
            return false;
        }
    }

    // --- BỔ SUNG: HÀM LẤY ĐƠN HÀNG THEO ID (Để kiểm tra trước khi cập nhật) ---
    public Order getOrderById(int id) {
        try {
            String sql = "SELECT * FROM orders WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Order(
                    rs.getInt("id"),
                    rs.getString("customer_name"),
                    rs.getString("phone"),
                    rs.getString("address"),
                    rs.getDouble("total_amount"),
                    rs.getString("status")
                );
            }
        } catch (Exception e) {
            System.out.println("Lỗi getOrderById: " + e.getMessage());
        }
        return null;
    }

    // 3. Hàm lấy toàn bộ Đơn hàng kèm Chi tiết (Giữ nguyên của bạn)
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try {
            String sqlOrder = "SELECT * FROM orders ORDER BY id DESC";
            PreparedStatement psOrder = connection.prepareStatement(sqlOrder);
            ResultSet rsOrder = psOrder.executeQuery();
            
            while(rsOrder.next()) {
                int orderId = rsOrder.getInt("id");
                Order order = new Order(
                    orderId,
                    rsOrder.getString("customer_name"),
                    rsOrder.getString("phone"),
                    rsOrder.getString("address"),
                    rsOrder.getDouble("total_amount"),
                    rsOrder.getString("status")
                );
                
// Tìm trong hàm getAllOrders() và thay đoạn lấy chi tiết bằng đoạn này:

String sqlDetail = "SELECT od.product_id, od.quantity, od.price, p.name, p.image " +
                   "FROM order_details od JOIN products p ON od.product_id = p.id " +
                   "WHERE od.order_id = ?";
PreparedStatement psDetail = connection.prepareStatement(sqlDetail);
psDetail.setInt(1, orderId);
ResultSet rsDetail = psDetail.executeQuery();

List<OrderDetail> details = new ArrayList<>();
while(rsDetail.next()) {
    // Truyền thêm rsDetail.getInt("product_id") vào đầu Constructor
    details.add(new OrderDetail(
        rsDetail.getInt("product_id"), 
        rsDetail.getString("name"),
        rsDetail.getString("image"),
        rsDetail.getInt("quantity"),
        rsDetail.getDouble("price")
    ));
}
                
                order.setDetails(details); 
                orders.add(order);
            }
        } catch(Exception e) {
            System.out.println("Lỗi getAllOrders: " + e.getMessage());
        }
        return orders;
    }
}