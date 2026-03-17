package filter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class AdminAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        
        // Kiểm tra xem trong Session có cái thẻ "adminLogged" chưa?
        if (session.getAttribute("adminLogged") != null) {
            // Có thẻ -> Cho phép đi tiếp vào trang Admin
            chain.doFilter(request, response);
        } else {
            // Không có thẻ -> Bắt quay xe ra trang Login ngay lập tức
            resp.sendRedirect("admin-login");
        }
    }
}