package servlet;

import dao.ProductDAO;
import model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class LoadProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        String id_raw = req.getParameter("id");
        try {
            int id = Integer.parseInt(id_raw);
            ProductDAO dao = new ProductDAO();
            Product p = dao.getProductById(id); // Lấy sản phẩm cũ
            
            req.setAttribute("detail", p); // Đóng gói gửi đi với tên là 'detail'
            req.getRequestDispatcher("/edit-product.jsp").forward(req, resp);
            
        } catch (Exception e) {
            System.out.println("Lỗi LoadProduct: " + e);
        }
    }
}