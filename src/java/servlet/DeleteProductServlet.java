package servlet;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class DeleteProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        // 1. Lấy ID của sản phẩm từ trên thanh URL xuống
        String id_raw = req.getParameter("id");
        
        try {
            int id = Integer.parseInt(id_raw);
            
            // 2. Gọi DAO để thực hiện lệnh Xóa
            ProductDAO dao = new ProductDAO();
            dao.deleteProduct(id);
            
        } catch (Exception e) {
            System.out.println("Lỗi ở DeleteServlet: " + e);
        }
        
        // 3. Xóa xong thì đuổi cổ thẳng về lại trang admin để xem bảng mới
        resp.sendRedirect("admin");
    }
}