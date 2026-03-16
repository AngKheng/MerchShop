package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import model.CartItem;
import java.io.IOException;
import java.util.List;

public class RemoveFromCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("id"));

        HttpSession session = req.getSession();
        @SuppressWarnings("unchecked")
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (cart != null) {
            cart.removeIf(item -> item.getProduct().getId() == productId);
            session.setAttribute("cart", cart);
        }

        resp.sendRedirect("cart");
    }
}