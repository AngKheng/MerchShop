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
        
        // Dòng này để xác nhận tín hiệu đã vào tới Code Java
        System.out.println("--- [SEPAY WEBHOOK CALLED] ---");
        
        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }

        String rawData = sb.toString();
        System.out.println("-> [DEBUG] Raw Data: " + rawData);

        try {
            if (rawData.isEmpty()) {
                System.out.println("-> [ERROR] Payload bi trong!");
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
                    if (Character.isDigit(c)) {
                        sbId.append(c);
                    } else {
                        break; 
                    }
                }
                orderIdStr = sbId.toString();
            }
            
            if (!orderIdStr.isEmpty()) {
                int orderId = Integer.parseInt(orderIdStr);
                OrderDAO dao = new OrderDAO();
                boolean success = dao.updateOrderStatus(orderId, "Đã thanh toán (VietQR-SePay)");
                
                if(success) {
                    System.out.println("-> [SUCCESS] Da duyet don hang #" + orderId);
                } else {
                    System.out.println("-> [DB ERROR] Khong the update don hang #" + orderId);
                }
            }

            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"success\": true}");
            response.getWriter().flush();

        } catch (Exception e) {
            System.err.println("-> [CRITICAL ERROR] " + e.getMessage());
            e.printStackTrace();
            // Tra ve 200 de SePay hien mau xanh, minh de debug hon
            response.setStatus(200); 
            response.getWriter().write("{\"success\": false, \"error\": \"" + e.getMessage() + "\"}");
        }
    }
}