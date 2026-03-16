package servlet;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import model.Product;
import java.io.IOException;

public class ProductDetailServlet extends HttpServlet {
    private final ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        String idParam = req.getParameter("id");
        
        if (idParam != null && !idParam.isEmpty()) {
            int id = Integer.parseInt(idParam);
            Product product = productDAO.getProductById(id);

            if (product != null) {
                // Nếu tìm thấy, gửi dữ liệu sang trang product-detail.jsp
                req.setAttribute("product", product);
                req.getRequestDispatcher("/product-detail.jsp").forward(req, resp);
                return;
            }
        }
        // Nếu không có ID hoặc không tìm thấy sản phẩm, đẩy về trang chủ
        resp.sendRedirect("home");
    }
}   