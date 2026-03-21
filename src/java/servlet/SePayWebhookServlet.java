package servlet;

import dao.OrderDAO;
import dao.ProductDAO;
import model.OrderDetail;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SePayWebhook", urlPatterns = {"/sepay-webhook"})
public class SePayWebhookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Đọc dữ liệu JSON từ SePay gửi qua
        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }

        try {
            // 2. Phân tích JSON (Cần thư viện GSON)
            JsonObject json = JsonParser.parseString(sb.toString()).getAsJsonObject();
            String content = json.get("content").getAsString(); // Ví dụ: "DH123"
            
            // 3. Tách lấy ID đơn hàng (Chỉ lấy các con số)
            String orderIdStr = content.replaceAll("[^0-9]", ""); 
            
            if (!orderIdStr.isEmpty()) {
                int orderId = Integer.parseInt(orderIdStr);
                OrderDAO oDao = new OrderDAO();
                ProductDAO pDao = new ProductDAO();

                // Kiểm tra xem đơn hàng có đang ở trạng thái "Chờ thanh toán" không
                // Tránh việc SePay bắn tin 2 lần gây trừ kho 2 lần
                if (oDao.getOrderById(orderId) != null && oDao.getOrderById(orderId).getStatus().equals("Chờ thanh toán")) {
                    
                    // A. Cập nhật trạng thái thành Đã thanh toán
                    oDao.updateOrderStatus(orderId, "Đã thanh toán (VietQR-SePay)");

                    // B. Tự động TRỪ KHO (Giống logic VNPay của bạn)
                    // Ta cần lấy danh sách sản phẩm của đơn hàng này để trừ
                    // Lưu ý: Bạn cần đảm bảo OrderDAO có hàm lấy chi tiết theo OrderID
                    // Ở đây mình giả định hàm getAllOrders đã có list details
                    List<OrderDetail> details = oDao.getOrderById(orderId).getDetails();
                    if (details != null) {
                        for (OrderDetail item : details) {
                            // Gọi hàm trừ kho của bạn
                            // Bạn xem lại ProductDAO xem hàm này tên gì nhé (ví dụ updateProductQuantity)
                            pDao.updateProductQuantity(item.getProductId(), item.getQuantity());
                        }
                    }
                    System.out.println("成功: Đã duyệt đơn #" + orderId + " và trừ kho.");
                }
                
                response.setStatus(HttpServletResponse.SC_OK); // Trả về 200 cho SePay
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}