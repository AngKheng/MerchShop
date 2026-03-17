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
        System.out.println("=== BẮT ĐẦU XỬ LÝ UPLOAD ===");

        try {
            String name = req.getParameter("name");
            String price = req.getParameter("price");
            System.out.println("1. Đã lấy tên: " + name + ", Giá: " + price);

            Part filePart = req.getPart("imageFile"); 
            System.out.println("2. Đã nhận được filePart từ Form.");

            if (filePart != null && filePart.getSize() > 0) {
                System.out.println("3. Kích thước file: " + filePart.getSize() + " bytes. Đang đọc mảng byte...");
                byte[] imageBytes = filePart.getInputStream().readAllBytes();
                System.out.println("4. Đọc file xong! Đang gọi Cloudinary...");

                Cloudinary cloudinary = CloudinaryConfig.getInstance();
                System.out.println("5. Đã lấy được cấu hình Cloudinary. Bắt đầu đẩy lên mây (bước này thường hay kẹt nhất)...");

                // Dòng này là dòng hay bị treo nhất nếu thiếu thư viện hoặc sai API
                Map uploadResult = cloudinary.uploader().upload(imageBytes, ObjectUtils.emptyMap());

                System.out.println("6. Upload Cloudinary XONG!");
               // ... (code cũ lấy imageUrl)
                String imageUrl = (String) uploadResult.get("secure_url");
                System.out.println("7. Link ảnh: " + imageUrl);
                
                // --- ĐOẠN CODE MỚI THÊM VÀO ---
                // Chuyển đổi giá tiền từ dạng Chữ (String) sang dạng Số thực (Double)
                double priceValue = Double.parseDouble(price);
                // Giả sử form của bạn chưa có ô nhập mô tả, mình gán tạm một câu. 
                // Nếu form có rồi thì bạn lấy req.getParameter("description") nhé.
                String description = "Mô tả sản phẩm đang cập nhật..."; 
                
                // Gọi DAO để lưu vào DB
                ProductDAO dao = new ProductDAO();
                dao.insertProduct(name, priceValue, imageUrl, description);
                
                // Sau khi lưu xong, đẩy thẳng người dùng về trang chủ để xem thành quả
                resp.sendRedirect("home");
                // ------------------------------
                
                resp.getWriter().println("<h3>Upload thành công! Link: " + imageUrl + "</h3>");
                
            } else {
                System.out.println("-> Lỗi: Không tìm thấy file hoặc file rỗng!");
                resp.getWriter().println("Bạn chưa chọn file ảnh!");
            }

        } catch (Exception e) {
            System.out.println("!!! CÓ LỖI VĂNG RA !!!");
            e.printStackTrace(); // In toàn bộ chi tiết lỗi ra console
            resp.getWriter().println("Lỗi quá trình upload: " + e.getMessage());
        }
        
        System.out.println("=== KẾT THÚC XỬ LÝ ===");
    }
}