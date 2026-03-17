package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // Gọi trang admin.jsp lên hiển thị
        req.getRequestDispatcher("/admin.jsp").forward(req, resp);
    }
}