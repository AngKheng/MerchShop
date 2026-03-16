package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class ContactServlet extends HttpServlet {

    // Hiển thị trang giao diện Contact
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        req.getRequestDispatcher("/contact.jsp").forward(req, resp);
    }

    // Xử lý khi người dùng bấm nút "Send message"
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        // Lấy dữ liệu người dùng nhập (sau này bạn có thể dùng để lưu vào Database hoặc gửi qua Email thật)
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String subject = req.getParameter("subject");
        String message = req.getParameter("message");

        // Giả lập xử lý thành công. Gắn một câu thông báo để hiển thị ra màn hình
        req.setAttribute("successMessage", "Đã gửi thành công! Cảm ơn bạn đã liên hệ, chúng tôi sẽ phản hồi sớm nhất có thể.");
        
        // Trả lại về trang contact.jsp để hiển thị thông báo
        req.getRequestDispatcher("/contact.jsp").forward(req, resp);
    }
}