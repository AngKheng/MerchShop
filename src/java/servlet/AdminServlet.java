package servlet;

import dao.ProductDAO;
import model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        // Gọi DAO vào kho lấy toàn bộ danh sách sản phẩm
        ProductDAO dao = new ProductDAO();
        List<Product> listP = dao.getAllProducts();
        
        // Đóng gói gửi sang trang admin.jsp
        req.setAttribute("listP", listP);
        req.setAttribute("totalProducts", listP.size()); // Gửi luôn con số tổng để in ra Dashboard
        
        req.getRequestDispatcher("/admin.jsp").forward(req, resp);
    }
}