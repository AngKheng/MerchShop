package servlet;

import model.CartItem;
import utils.VNPayConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        // Cài đặt UTF-8 để đọc tiếng Việt có dấu (Tên, Địa chỉ) không bị lỗi font
        req.setCharacterEncoding("UTF-8");
        
        // 1. Lấy thông tin khách hàng từ Form checkout.jsp
        String cusName = req.getParameter("cusName");
        String cusPhone = req.getParameter("cusPhone");
        String cusAddress = req.getParameter("cusAddress");
        String cusEmail = req.getParameter("cusEmail");

        // 2. Cất tạm vào Session. Lát nữa VNPay trả về thành công, mình sẽ lấy mấy cái này ra lưu Database!
        HttpSession session = req.getSession();
        if (cusName != null) {
            session.setAttribute("cusName", cusName);
            session.setAttribute("cusPhone", cusPhone);
            session.setAttribute("cusAddress", cusAddress);
            session.setAttribute("cusEmail", cusEmail);
        }
        
        // 3. Tính tổng tiền từ Giỏ hàng (Session)
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        
        if (cart == null || cart.isEmpty()) {
            resp.sendRedirect("home"); // Giỏ trống thì đuổi về trang chủ
            return;
        }

        double totalUSD = 0;
        for (CartItem item : cart) {
            totalUSD += item.getProduct().getPrice() * item.getQuantity();
        }

        // 4. Quy đổi USD sang VNĐ (Giả định 1$ = 25.000đ) và nhân 100 theo luật của VNPay
        long amountInVND = (long) (totalUSD * 25000);
        long vnp_Amount = amountInVND * 100; 
        
        // 5. Chuẩn bị các tham số gửi sang VNPay
        String vnp_TxnRef = VNPayConfig.getRandomNumber(8); // Mã đơn hàng ngẫu nhiên
        String vnp_IpAddr = "127.0.0.1"; // IP local
        String vnp_TmnCode = VNPayConfig.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", "2.1.0");
        vnp_Params.put("vnp_Command", "pay");
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(vnp_Amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode", "NCB"); // Ép dùng ngân hàng NCB để test cho lẹ
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang MerchShop " + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", "other");
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", VNPayConfig.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        // 6. Tạo ngày giờ tạo đơn và giờ hết hạn (15 phút)
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        
        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        // 7. Build chuỗi Query và Mã hóa bảo mật (Chuẩn hóa của VNPay)
        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator<String> itr = fieldNames.iterator();
        
        while (itr.hasNext()) {
            String fieldName = itr.next();
            String fieldValue = vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                // Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                // Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        
        String queryUrl = query.toString();
        // Dùng HashSecret để khóa dữ liệu lại
        String vnp_SecureHash = VNPayConfig.hmacSHA512(VNPayConfig.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        
        // 8. Gắn vào link gốc của VNPay và Bẻ lái trình duyệt bay đi!
        String paymentUrl = VNPayConfig.vnp_PayUrl + "?" + queryUrl;
        resp.sendRedirect(paymentUrl);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // Chuyển toàn bộ yêu cầu từ POST sang cho hàm doGet xử lý
        doGet(req, resp); 
    }
}