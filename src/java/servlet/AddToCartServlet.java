package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import model.CartItem;
import model.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddToCartServlet extends HttpServlet {

    private List<Product> getSampleProducts() {
        // giống hàm ở HomeServlet (có thể tách ra class chung sau)
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Gaku Doll", 16.00, "..."));
        products.add(new Product(2, "Yugamu Doll", 16.00, "..."));
        products.add(new Product(3, "Doll Extra", 2.60, "..."));
        products.add(new Product(4, "Cherino Hot Spring", 5.00, "..."));
        products.add(new Product(5, "OMG Kaw", 4.50, "image/omgkaw.png"));
        return products;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("id"));

        HttpSession session = req.getSession();
        @SuppressWarnings("unchecked")
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        Product product = getSampleProducts().stream()
                .filter(p -> p.getId() == productId)
                .findFirst().orElse(null);

        if (product != null) {
            boolean found = false;
            for (CartItem item : cart) {
                if (item.getProduct().getId() == productId) {
                    item.setQuantity(item.getQuantity() + 1);
                    found = true;
                    break;
                }
            }
            if (!found) {
                cart.add(new CartItem(product, 1));
            }
        }

        session.setAttribute("cart", cart);
        resp.sendRedirect("home");
    }
}