package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class CheckoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("cart");

        req.setAttribute("message", "Thanh toán thành công! Cảm ơn bạn đã mua hàng.");
        req.getRequestDispatcher("/cart.jsp").forward(req, resp);
    }
}