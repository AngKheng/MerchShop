package servlet;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import model.Product;
import java.io.IOException;
import java.util.List;

public class HomeServlet extends HttpServlet {

    private final ProductDAO productDAO = new ProductDAO();

@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        String type = req.getParameter("type");
        String search = req.getParameter("search"); // Lấy chữ khách gõ từ thanh tìm kiếm
        
        ProductDAO dao = new ProductDAO();
        List<Product> list;

        // Phân luồng logic
        if (search != null && !search.trim().isEmpty()) {
            // 1. Nếu có gõ tìm kiếm
            list = dao.searchProductsByName(search);
        } else if (type != null && !type.equals("All")) {
            // 2. Nếu bấm chọn danh mục
            list = dao.getProductsByType(type);
            req.setAttribute("selectedCategory", type);
        } else {
            // 3. Mặc định hiện tất cả
            list = dao.getAllProducts();
            req.setAttribute("selectedCategory", "All");
        }

        req.setAttribute("products", list);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}