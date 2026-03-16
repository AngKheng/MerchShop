package dao;

import model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    // Dùng static để danh sách này chỉ tạo 1 lần và tồn tại mãi trong bộ nhớ
    private static final List<Product> products = new ArrayList<>();

    // Khối static khởi tạo dữ liệu
    static {
        products.add(new Product(1, "Gaku Doll", 16.00, "image/1.png"));
        products.add(new Product(2, "Yugamu Doll", 16.00, "image/2.png"));
        products.add(new Product(3, "Doll Extra", 2.60, "image/3.png"));
        products.add(new Product(4, "Cherino Hot Spring", 5.00, "image/cherino-hot-spring.png"));
        products.add(new Product(5, "OMG Kaw", 4.50, "image/omgkaw.png"));
        
        // Thử thêm sản phẩm số 6 của bạn vào đây:
//        products.add(new Product(6, "abc", 4.50, "image/abc.png"));
//        products.add(new Product(7, "ád", 4.50, "image/abc.png"));
        
    }

    // Hàm trả về toàn bộ danh sách
    public List<Product> getAllProducts() {
        return products;
    }

    // Hàm tìm sản phẩm theo ID
    public Product getProductById(int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
}