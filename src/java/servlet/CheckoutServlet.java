package servlet;

import dao.OrderDAO;
import model.CartItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        // Cài đặt UTF-8 để đọc tiếng Việt không bị lỗi font
        req.setCharacterEncoding("UTF-8");
        
        // 1. Lấy thông tin khách hàng từ Form checkout.jsp
        String cusName = req.getParameter("cusName");
        String cusPhone = req.getParameter("cusPhone");
        String cusAddress = req.getParameter("cusAddress");
        String cusEmail = req.getParameter("cusEmail");

        // 2. Kiểm tra Giỏ hàng từ Session
        HttpSession session = req.getSession();
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        
        if (cart == null || cart.isEmpty()) {
            resp.sendRedirect("home"); 
            return;
        }

        // 3. Tính tổng tiền (USD)
        double totalUSD = 0;
        for (CartItem item : cart) {
            totalUSD += item.getProduct().getPrice() * item.getQuantity();
        }

        // 4. Quy đổi sang VNĐ để hiện trên mã QR (Giả định 1$ = 25.000đ)
        long amountInVND = (long) (totalUSD * 25000);
        
        // 5. LƯU ĐƠN HÀNG VÀO DATABASE NGAY LẬP TỨC (Trạng thái: Chờ thanh toán)
        OrderDAO orderDao = new OrderDAO();
        int newOrderId = orderDao.insertOrderReturnId(cusName, cusPhone, cusAddress, cusEmail, totalUSD, "Chờ thanh toán");

        if (newOrderId > 0) {
            // 6. Lưu chi tiết từng món hàng vào bảng order_details
            for (CartItem item : cart) {
                orderDao.insertOrderDetail(newOrderId, item.getProduct().getId(), item.getQuantity(), item.getProduct().getPrice());
            }
            
            // 7. Cất thông tin vào Session để trang waiting-payment.jsp hiển thị
            session.setAttribute("lastOrderId", newOrderId);
            session.setAttribute("lastAmount", amountInVND);
            
            // 8. DỌN DẸP GIỎ HÀNG (Vì đơn đã được lưu vào Database an toàn rồi)
            session.removeAttribute("cart");

            // 9. CHUYỂN HƯỚNG SANG TRANG CHỜ THANH TOÁN QR
            resp.sendRedirect("waiting-payment.jsp");
            
        } else {
            // Nếu lưu DB thất bại, báo lỗi
            resp.getWriter().println("Lỗi: Không thể khởi tạo đơn hàng. Vui lòng thử lại!");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // Nếu khách cố tình truy cập link qua GET, chuyển về trang chủ
        resp.sendRedirect("home");
    }
}