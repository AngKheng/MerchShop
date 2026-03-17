package servlet;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import utils.CloudinaryConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Map;

// Annotation này bắt buộc để nhận file
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB tối đa cho 1 file
    maxRequestSize = 1024 * 1024 * 50    // 50MB tối đa cho toàn bộ request
)

public class AddProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        req.getRequestDispatcher("/add-product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        resp.setContentType("text/html;charset=UTF-8");
        
        // 1. Lấy dữ liệu text bình thường
        String name = req.getParameter("name");
        String price = req.getParameter("price");

        // 2. Lấy file ảnh gửi lên từ input type="file" có name="imageFile"
        Part filePart = req.getPart("imageFile"); 
        
        try {
            if (filePart != null && filePart.getSize() > 0) {
                
                // Đọc file thành mảng byte
                byte[] imageBytes = filePart.getInputStream().readAllBytes();

                // 3. Gọi Cloudinary đẩy ảnh lên
                Cloudinary cloudinary = CloudinaryConfig.getInstance();
                Map uploadResult = cloudinary.uploader().upload(imageBytes, ObjectUtils.emptyMap());

                // 4. LẤY ĐƯỜNG LINK (URL) TRẢ VỀ
                String imageUrl = (String) uploadResult.get("secure_url");
                
                // In ra màn hình web để bạn kiểm tra xem thành công chưa
                resp.getWriter().println("<h3>Upload lên Cloudinary THÀNH CÔNG!</h3>");
                resp.getWriter().println("<p>Sản phẩm: " + name + " - Giá: $" + price + "</p>");
                resp.getWriter().println("<p>Link ảnh (URL) của bạn là: <a href='" + imageUrl + "' target='_blank'>" + imageUrl + "</a></p>");
                resp.getWriter().println("<img src='" + imageUrl + "' width='200px' />");
                
                /* * BƯỚC TIẾP THEO (Sau khi test thành công):
                 * Bạn sẽ dùng DBContext gọi ProductDAO ở đây:
                 * productDAO.insertProduct(name, price, imageUrl, description);
                 */
            } else {
                resp.getWriter().println("Bạn chưa chọn file ảnh!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("Lỗi quá trình upload: " + e.getMessage());
        }
    }
}