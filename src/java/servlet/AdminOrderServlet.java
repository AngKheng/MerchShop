package servlet;

import dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class AdminOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        OrderDAO dao = new OrderDAO();
        req.setAttribute("listOrders", dao.getAllOrders());
        
        req.getRequestDispatcher("/admin-orders.jsp").forward(req, resp);
    }
}