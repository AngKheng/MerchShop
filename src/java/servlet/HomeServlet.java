package servlet;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import model.Product;
import java.io.IOException;
import java.util.List;

public class HomeServlet extends HttpServlet {

    // Khởi tạo trực tiếp từ class ProductDAO
    private final ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        List<Product> products = productDAO.getAllProducts();
        
        System.out.println("Số lượng sản phẩm gửi sang JSP: " + products.size());
        
        req.setAttribute("products", products);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}