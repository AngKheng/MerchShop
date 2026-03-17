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
            // Statement.RETURN_GENERATED_KEYS giúp lấy lại ID vừa được SQL tự động tạo ra
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, address);
            ps.setString(4, email);
            ps.setDouble(5, totalAmount);
            ps.setString(6, status);
            
            ps.executeUpdate();
            
            // Lấy ID vừa tạo
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // Trả về mã Order ID (Ví dụ: Đơn số 5)
            }
        } catch (Exception e) {
            System.out.println("Lỗi insertOrder: " + e.getMessage());
        }
        return 0; // Trả về 0 nếu thất bại
    }

    // 2. Lưu từng món hàng khách đã chọn vào bảng order_details
    public void insertOrderDetail(int orderId, int productId, int quantity, double price) {
        String sql = "INSERT INTO order_details (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.setInt(2, productId);
            ps.setInt(3, quantity);
            ps.setDouble(4, price); // Lưu lại giá lúc mua (đề phòng sau này sp tăng/giảm giá)
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Lỗi insertOrderDetail: " + e.getMessage());
        }
    }

    // 3. Hàm lấy toàn bộ Đơn hàng kèm Chi tiết
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try {
            // Lấy danh sách đơn hàng
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
                
                // Móc nối bảng order_details với products để lấy tên và ảnh
                String sqlDetail = "SELECT od.quantity, od.price, p.name, p.image FROM order_details od JOIN products p ON od.product_id = p.id WHERE od.order_id = ?";
                PreparedStatement psDetail = connection.prepareStatement(sqlDetail);
                psDetail.setInt(1, orderId);
                ResultSet rsDetail = psDetail.executeQuery();
                
                List<OrderDetail> details = new ArrayList<>();
                while(rsDetail.next()) {
                    details.add(new OrderDetail(
                        rsDetail.getString("name"),
                        rsDetail.getString("image"),
                        rsDetail.getInt("quantity"),
                        rsDetail.getDouble("price")
                    ));
                }
                
                order.setDetails(details); // Nhét các món hàng vào Đơn
                orders.add(order);
            }
        } catch(Exception e) {
            System.out.println("Lỗi getAllOrders: " + e.getMessage());
        }
        return orders;
    }
}