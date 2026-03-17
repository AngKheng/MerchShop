package servlet;

import dao.OrderDAO;
import model.CartItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "VNPayReturnServlet", urlPatterns = {"/vnpay-return"})
public class VNPayReturnServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        resp.setContentType("text/html;charset=UTF-8");
        String vnp_ResponseCode = req.getParameter("vnp_ResponseCode");
        
        if ("00".equals(vnp_ResponseCode)) {
            // 1. LẤY THÔNG TIN TỪ SESSION RA
            HttpSession session = req.getSession();
            String name = (String) session.getAttribute("cusName");
            String phone = (String) session.getAttribute("cusPhone");
            String address = (String) session.getAttribute("cusAddress");
            String email = (String) session.getAttribute("cusEmail");
            
            // 2. TÍNH TỔNG TIỀN TỪ GIỎ HÀNG
            List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
            double totalAmount = 0;
            if (cart != null) {
                for (CartItem item : cart) {
                    totalAmount += item.getProduct().getPrice() * item.getQuantity();
                }
            }
            
// 3. LƯU VÀO DATABASE (Cả Đơn hàng và Chi tiết đơn)
            if (name != null) {
                OrderDAO dao = new OrderDAO();
                
                // Bước 3.1: Lưu thông tin khách và lấy ID Đơn hàng về
                int newOrderId = dao.insertOrderReturnId(name, phone, address, email, totalAmount, "Đã thanh toán (VNPay)");
                
                // Bước 3.2: Nếu tạo đơn thành công, vòng lặp lưu từng món trong giỏ vào Database
                if (newOrderId > 0 && cart != null) {
                    for (CartItem item : cart) {
                        dao.insertOrderDetail(newOrderId, item.getProduct().getId(), item.getQuantity(), item.getProduct().getPrice());
                    }
                }
            }
            
            // 4. DỌN DẸP SESSION (Xóa giỏ hàng và thông tin cá nhân)
            session.removeAttribute("cart");
            session.removeAttribute("cusName");
            session.removeAttribute("cusPhone");
            session.removeAttribute("cusAddress");
            session.removeAttribute("cusEmail");
            
            // 5. HIỂN THỊ THÀNH CÔNG
            resp.getWriter().println("<div style='font-family: Arial; text-align: center; margin-top: 50px;'>");
            resp.getWriter().println("<h1 style='color: #4CAF50;'>THANH TOÁN THÀNH CÔNG!</h1>");
            resp.getWriter().println("<h3>Cảm ơn <b>" + name + "</b> đã mua sắm tại MerchShop.</h3>");
            resp.getWriter().println("<p>Tổng số tiền thanh toán: <b>$" + totalAmount + "</b></p>");
            resp.getWriter().println("<p>Đơn hàng sẽ được gửi đến: <b>" + address + "</b></p>");
            resp.getWriter().println("<p>Biên lai đã được gửi qua email: " + email + "</p>");
            resp.getWriter().println("<br><a href='home' style='padding: 10px 20px; background: #333; color: white; text-decoration: none; border-radius: 5px;'>Quay về Trang chủ</a>");
            resp.getWriter().println("</div>");
            
        } else {
            // Thất bại
            resp.getWriter().println("<h1 style='color: red; text-align: center; margin-top: 50px;'>THANH TOÁN THẤT BẠI HOẶC BỊ HỦY!</h1>");
            resp.getWriter().println("<div style='text-align: center;'><a href='cart'>Quay lại Giỏ hàng</a></div>");
        }
    }
}