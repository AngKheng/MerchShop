package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class AdminLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // Mở trang form đăng nhập
        req.getRequestDispatcher("/admin-login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        String user = req.getParameter("username");
        String pass = req.getParameter("password");
        
        // KIỂM TRA TÀI KHOẢN VÀ MẬT KHẨU
        if ("admin".equals(user) && "admin123".equals(pass)) {
            
            // Cấp "Thẻ thông hành" vào Session
            HttpSession session = req.getSession();
            session.setAttribute("adminLogged", true);
            
            // Đăng nhập thành công -> Mở cửa cho vào trang Admin
            resp.sendRedirect("admin");
            
        } else {
            // Sai pass -> Đuổi về bắt nhập lại
            req.setAttribute("error", "Tài khoản hoặc mật khẩu không chính xác!");
            req.getRequestDispatcher("/admin-login.jsp").forward(req, resp);
        }
    }
}