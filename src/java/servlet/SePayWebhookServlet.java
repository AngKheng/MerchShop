package servlet;

import dao.OrderDAO;
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
        
        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }

        try {
            // 1. Đọc dữ liệu JSON
            JsonObject json = JsonParser.parseString(sb.toString()).getAsJsonObject();
            String content = json.get("content").getAsString(); // Ví dụ: "DH123 thanh toan..."
            
            // 2. Tách ID đơn hàng thông minh (Chỉ lấy số sau chữ DH)
            String orderIdStr = "";
            int index = content.toUpperCase().indexOf("DH");

            if (index != -1) {
                // Lấy phần chuỗi bắt đầu từ sau chữ "DH"
                String sauDH = content.substring(index + 2).trim();
                
                // Duyệt để lấy các con số liên tiếp ngay sau đó
                StringBuilder sbId = new StringBuilder();
                for (int i = 0; i < sauDH.length(); i++) {
                    char c = sauDH.charAt(i);
                    if (Character.isDigit(c)) {
                        sbId.append(c);
                    } else {
                        // Gặp khoảng trắng hoặc ký tự khác thì dừng lại
                        break; 
                    }
                }
                orderIdStr = sbId.toString();
            }
            
            // 3. Xử lý cập nhật Database
            if (!orderIdStr.isEmpty()) {
                // Dùng Long.parseLong cho an toàn hoặc Integer nếu mã đơn hàng của bạn ngắn
                int orderId = Integer.parseInt(orderIdStr);
                OrderDAO dao = new OrderDAO();
                
                // Cập nhật trạng thái
                dao.updateOrderStatus(orderId, "Đã thanh toán (VietQR-SePay)");
                System.out.println("-> [SEPAY] Da duyet don hang #" + orderId);
            } else {
                System.out.println("-> [SEPAY] Khong tim thay ma DH trong noi dung: " + content);
            }

            // 4. Phản hồi bắt buộc cho SePay
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"success\": true}");
            response.getWriter().flush();

        } catch (Exception e) {
            System.err.println("-> [SEPAY ERROR] " + e.getMessage());
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}