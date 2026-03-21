package servlet;

import dao.OrderDAO;
import dao.ProductDAO;
import model.Order;
import model.OrderDetail;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;

public class SePayWebhookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("--- [SEPAY WEBHOOK CALLED] ---");
        
        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }

        String rawData = sb.toString();
        try {
            if (rawData.isEmpty()) {
                response.setStatus(400);
                return;
            }

            JsonObject json = JsonParser.parseString(rawData).getAsJsonObject();
            String content = json.get("content").getAsString(); 
            
            String orderIdStr = "";
            int index = content.toUpperCase().indexOf("DH");

            if (index != -1) {
                String sauDH = content.substring(index + 2).trim();
                StringBuilder sbId = new StringBuilder();
                for (int i = 0; i < sauDH.length(); i++) {
                    char c = sauDH.charAt(i);
                    if (Character.isDigit(c)) { sbId.append(c); } else { break; }
                }
                orderIdStr = sbId.toString();
            }
            
            if (!orderIdStr.isEmpty()) {
                int orderId = Integer.parseInt(orderIdStr);
                OrderDAO oDao = new OrderDAO();
                
                // 1. Lấy thông tin đơn hàng hiện tại từ DB
                Order order = oDao.getOrderById(orderId);
                
                // Chỉ xử lý nếu đơn hàng tồn tại và CHƯA được thanh toán trước đó
                if (order != null && !order.getStatus().contains("Đã thanh toán")) {
                    
                    // 2. Cập nhật trạng thái đơn hàng thành Đã thanh toán
                    boolean updateOrderOk = oDao.updateOrderStatus(orderId, "Đã thanh toán (VietQR-SePay)");
                    
                    if (updateOrderOk) {
                        System.out.println("-> [SUCCESS] Da duyet don hang #" + orderId);
                        
                        // 3. THỰC HIỆN TRỪ KHO
                        ProductDAO pDao = new ProductDAO();
                        if (order.getDetails() != null && !order.getDetails().isEmpty()) {
                            for (OrderDetail detail : order.getDetails()) {
                                // Gọi hàm trừ kho mà bạn đã viết trong ProductDAO
                                pDao.updateProductQuantity(detail.getProductId(), detail.getQuantity());
                                System.out.println("-> [INVENTORY] Da tru " + detail.getQuantity() + " SP ID: " + detail.getProductId());
                            }
                        }
                    }
                } else {
                    System.out.println("-> [INFO] Don hang #" + orderId + " khong ton tai hoac da thanh toan tu truoc.");
                }
            }

            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"success\": true}");
            response.getWriter().flush();

        } catch (Exception e) {
            System.err.println("-> [CRITICAL ERROR] " + e.getMessage());
            e.printStackTrace();
            response.setStatus(200); 
            response.getWriter().write("{\"success\": false}");
        }
    }
}