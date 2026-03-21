package servlet;

import dao.OrderDAO;
import model.Order;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

public class CheckStatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idStr = req.getParameter("id");
        if (idStr != null) {
            OrderDAO dao = new OrderDAO();
            Order o = dao.getOrderById(Integer.parseInt(idStr));
            // Nếu đơn hàng đã đổi trạng thái thành "Đã thanh toán..." thì báo về cho JavaScript
            if (o != null && o.getStatus().contains("Đã thanh toán")) {
                resp.getWriter().write("success");
                return;
            }
        }
        resp.getWriter().write("pending");
    }
}