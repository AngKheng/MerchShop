package servlet;

import dao.OrderDAO;
import model.Order;
import jakarta.servlet.http.*;
import java.io.IOException;

public class CheckStatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idStr = req.getParameter("id");
        if (idStr != null) {
            OrderDAO dao = new OrderDAO();
            Order o = dao.getOrderById(Integer.parseInt(idStr));
            
            // Kiểm tra chữ "Đã thanh toán" trong Status của DB
            if (o != null && o.getStatus().contains("Đã thanh toán")) {
                resp.getWriter().write("success");
                return;
            }
        }
        resp.getWriter().write("pending");
    }
}