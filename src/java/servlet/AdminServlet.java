package servlet;

import dao.OrderDAO;
import dao.ProductDAO;
import model.Order;
import model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        // 1. Vào kho đếm Tổng Sản Phẩm
        ProductDAO pDao = new ProductDAO();
        List<Product> listP = pDao.getAllProducts();
        
        // 2. Vào kho đếm Tổng Đơn Hàng
        OrderDAO oDao = new OrderDAO();
        List<Order> listO = oDao.getAllOrders();
        
        // 3. Đóng gói danh sách và các con số để gửi sang giao diện
        req.setAttribute("listP", listP);
        req.setAttribute("totalProducts", listP.size()); // Gửi biến đếm sản phẩm
        req.setAttribute("totalOrders", listO.size());   // Gửi biến đếm đơn hàng
        
        req.getRequestDispatcher("/admin.jsp").forward(req, resp);
    }
}