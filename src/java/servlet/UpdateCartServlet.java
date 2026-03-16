package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import model.CartItem;
import java.io.IOException;
import java.util.List;

public class UpdateCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        int productId = Integer.parseInt(req.getParameter("id"));
        String action = req.getParameter("action"); // Nhận hành động là "increase" (tăng) hoặc "decrease" (giảm)

        HttpSession session = req.getSession();
        @SuppressWarnings("unchecked")
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (cart != null) {
            // Tìm sản phẩm trong giỏ hàng
            for (int i = 0; i < cart.size(); i++) {
                CartItem item = cart.get(i);
                if (item.getProduct().getId() == productId) {
                    if ("increase".equals(action)) {
                        // Tăng số lượng thêm 1
                        item.setQuantity(item.getQuantity() + 1);
                    } else if ("decrease".equals(action)) {
                        // Giảm số lượng đi 1
                        item.setQuantity(item.getQuantity() - 1);
                        // Nếu số lượng tụt xuống 0 hoặc nhỏ hơn, tự động xóa khỏi giỏ
                        if (item.getQuantity() <= 0) {
                            cart.remove(i);
                        }
                    }
                    break; // Xử lý xong thì thoát vòng lặp
                }
            }
            // Cập nhật lại giỏ hàng vào session
            session.setAttribute("cart", cart);
        }

        // Quay trở lại trang giỏ hàng
        resp.sendRedirect("cart");
    }
}