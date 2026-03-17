package servlet;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import dao.ProductDAO;
import utils.CloudinaryConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Map;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB tối đa cho 1 file
    maxRequestSize = 1024 * 1024 * 50    // 50MB tối đa cho toàn bộ request
)
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // Mở trang giao diện thêm sản phẩm
        req.getRequestDispatcher("/add-product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        resp.setContentType("text/html;charset=UTF-8");
        System.out.println("=== BẮT ĐẦU XỬ LÝ UPLOAD VÀ LƯU DATABASE ===");

        try {
            // 1. Lấy toàn bộ dữ liệu chữ (Text) từ Form
            String name = req.getParameter("name");
            String price = req.getParameter("price");
            String type = req.getParameter("type");               
            String description = req.getParameter("description"); 

            // 2. Lấy dữ liệu file (Ảnh)
            Part filePart = req.getPart("imageFile"); 
            System.out.println("-> Đã nhận được dữ liệu từ Form.");

            if (filePart != null && filePart.getSize() > 0) {
                System.out.println("-> Đang đọc file ảnh...");
                byte[] imageBytes = filePart.getInputStream().readAllBytes();

                // 3. Đẩy ảnh lên Cloudinary
                System.out.println("-> Đang gọi Cloudinary để upload...");
                Cloudinary cloudinary = CloudinaryConfig.getInstance();
                Map uploadResult = cloudinary.uploader().upload(imageBytes, ObjectUtils.emptyMap());

                String imageUrl = (String) uploadResult.get("secure_url");
                System.out.println("-> Upload Cloudinary thành công! Link: " + imageUrl);

                // 4. Lưu thông tin vào Database SQL Server
                double priceValue = Double.parseDouble(price);
                ProductDAO dao = new ProductDAO();
                
                // Truyền 5 tham số vào Database
                dao.insertProduct(name, priceValue, imageUrl, description, type);
                System.out.println("-> Đã lưu vào Database!");
                
                // 5. Chuyển hướng thẳng về trang chủ (home)
                resp.sendRedirect("home");
                
            } else {
                System.out.println("-> LỖI: Không tìm thấy file hoặc file rỗng!");
                resp.getWriter().println("Bạn chưa chọn file ảnh!");
            }

        } catch (Exception e) {
            System.out.println("!!! CÓ LỖI XẢY RA !!!");
            e.printStackTrace(); 
            resp.getWriter().println("Lỗi quá trình upload: " + e.getMessage());
        }
        
        System.out.println("=== KẾT THÚC XỬ LÝ ===");
    }
}