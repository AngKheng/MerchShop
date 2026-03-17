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
        
        // 1. Lấy chữ mà người dùng vừa click trên thanh menu (ví dụ: "FFXIV")
        String type = req.getParameter("type");
        List<Product> products;

        // 2. Kiểm tra xem người dùng muốn xem gì
        if (type != null && !type.isEmpty() && !type.equals("All")) {
            // Nếu có chữ type và nó KHÔNG PHẢI là "All" -> Gọi hàm lọc
            products = productDAO.getProductsByType(type);
        } else {
            // Nếu vừa vào web (type null) hoặc bấm chữ "All" -> Gọi hàm lấy tất cả
            products = productDAO.getAllProducts();
        }
        
        // 3. Đóng gói sản phẩm gửi sang JSP
        req.setAttribute("products", products);
        
        // (Tùy chọn) Gửi kèm tên danh mục đang chọn để in ra màn hình cho đẹp
        req.setAttribute("selectedCategory", type != null ? type : "All");
        
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}