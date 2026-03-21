package servlet;

import dao.OrderDAO;
import dao.ProductDAO;
import model.Order;
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
            // 2. Phân tích JSON
            JsonObject json = JsonParser.parseString(sb.toString()).getAsJsonObject();
            String content = json.get("content").getAsString(); 
            
            // 3. Tách lấy ID đơn hàng (Ví dụ: "DH123" -> lấy 123)
            String orderIdStr = content.replaceAll("[^0-9]", ""); 
            
            if (!orderIdStr.isEmpty()) {
                int orderId = Integer.parseInt(orderIdStr);
                OrderDAO oDao = new OrderDAO();
                ProductDAO pDao = new ProductDAO();

                // Lấy đơn hàng hiện tại từ Database
                Order currentOrder = oDao.getOrderById(orderId);

                // KIỂM TRA: Đơn hàng tồn tại VÀ đang ở trạng thái Chờ thanh toán
                if (currentOrder != null && "Chờ thanh toán".equals(currentOrder.getStatus())) {
                    
                    // A. Cập nhật trạng thái thành Đã thanh toán
                    oDao.updateOrderStatus(orderId, "Đã thanh toán (VietQR-SePay)");

                    // B. Trừ kho
                    List<OrderDetail> details = currentOrder.getDetails();
                    if (details != null) {
                        for (OrderDetail item : details) {
                            pDao.updateProductQuantity(item.getProductId(), item.getQuantity());
                        }
                    }
                    System.out.println("-> [SEPAY] Duyet don #" + orderId + " thanh cong.");
                }
            }

            // --- BƯỚC QUAN TRỌNG: PHẢN HỒI JSON CHO SEPAY THEO HƯỚNG DẪN ---
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_OK); // Trả về 200
            response.getWriter().write("{\"success\": true}"); // Bắt buộc phải có chuỗi này
            response.getWriter().flush();

        } catch (Exception e) {
            e.printStackTrace();
            // Nếu lỗi hệ thống, trả về 400 để SePay biết và gửi lại sau
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}