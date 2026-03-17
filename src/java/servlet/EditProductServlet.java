package servlet;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import dao.ProductDAO;
import utils.CloudinaryConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Map;

// Phải có cái này để nhận được file ảnh nhé
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50
)
public class EditProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        req.setCharacterEncoding("UTF-8");
        
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));
            String type = req.getParameter("type");
            String description = req.getParameter("description");
            
            // Nhận các biến mới
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            double salePrice = Double.parseDouble(req.getParameter("salePrice"));
            
            // Checkbox: Nếu ko tích thì nó gửi về null
            boolean isSoldOut = req.getParameter("isSoldOut") != null; 
            
            // Xử lý Ảnh
            String oldImage = req.getParameter("oldImage");
            String finalImage = oldImage; // Mặc định là giữ ảnh cũ
            
            Part filePart = req.getPart("imageFile");
            if (filePart != null && filePart.getSize() > 0) {
                // Nếu khách có chọn file mới -> Đẩy lên Cloudinary lấy link mới
                byte[] imageBytes = filePart.getInputStream().readAllBytes();
                Cloudinary cloudinary = CloudinaryConfig.getInstance();
                Map uploadResult = cloudinary.uploader().upload(imageBytes, ObjectUtils.emptyMap());
                finalImage = (String) uploadResult.get("secure_url");
            }

            // Gọi DAO để cập nhật
            ProductDAO dao = new ProductDAO();
            dao.updateProduct(id, name, price, finalImage, description, type, quantity, salePrice, isSoldOut);
            
        } catch (Exception e) {
            System.out.println("Lỗi EditProduct: " + e.getMessage());
        }
        
        // Sửa xong thì quay về trang Admin
        resp.sendRedirect("admin");
    }
}