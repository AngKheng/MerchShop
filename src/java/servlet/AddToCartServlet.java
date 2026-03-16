package servlet;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import model.CartItem;
import model.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddToCartServlet extends HttpServlet {

    // Khởi tạo trực tiếp từ class ProductDAO
    private final ProductDAO productDAO = new ProductDAO();

@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("id"));
        
        // ĐỌC SỐ LƯỢNG TỪ FORM GỬI LÊN (Mặc định là 1 nếu không có)
        int quantityToAdd = 1;
        String qtyParam = req.getParameter("quantity");
        if (qtyParam != null && !qtyParam.isEmpty()) {
            quantityToAdd = Integer.parseInt(qtyParam);
        }

        HttpSession session = req.getSession();
        @SuppressWarnings("unchecked")
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        Product product = productDAO.getProductById(productId);

        if (product != null) {
            boolean found = false;
            for (CartItem item : cart) {
                if (item.getProduct().getId() == productId) {
                    // Cộng thêm đúng số lượng người dùng vừa nhập
                    item.setQuantity(item.getQuantity() + quantityToAdd);
                    found = true;
                    break;
                }
            }
            if (!found) {
                // Thêm sản phẩm mới với số lượng người dùng nhập
                cart.add(new CartItem(product, quantityToAdd));
            }
        }

        session.setAttribute("cart", cart);
        resp.sendRedirect("cart"); // Thêm xong thường đẩy thẳng ra trang Giỏ hàng cho tiện
    }
}