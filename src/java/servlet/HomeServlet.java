package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import model.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeServlet extends HttpServlet {

private List<Product> getSampleProducts() {
    List<Product> products = new ArrayList<>();

    products.add(new Product(1, "Gaku Doll", 16.00, "image/1.png"));
    products.add(new Product(2, "Yugamu Doll", 16.00, "image/2.png"));
    products.add(new Product(3, "Doll Extra", 2.60, "image/3.png"));
    products.add(new Product(4, "Cherino Hot Spring", 5.00, "image/cherino-hot-spring.png"));
    // Nếu có thêm sản phẩm: 
    products.add(new Product(5, "OMG Kaw", 4.50, "image/omgkaw.png"));

    return products;
}

@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {
    List<Product> products = getSampleProducts();
    
    System.out.println("Số lượng sản phẩm gửi sang JSP: " + products.size());
    
    req.setAttribute("products", products);
    req.getRequestDispatcher("/index.jsp").forward(req, resp);
}
}