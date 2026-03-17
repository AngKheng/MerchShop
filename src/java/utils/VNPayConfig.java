package utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class VNPayConfig {
    // 1. Các thông tin VNPay cung cấp qua Email
    public static String vnp_TmnCode = "EG8B4KNG";
    public static String vnp_HashSecret = "S3QN10N9Z4RLY7YRHUSOHND7H65MV5HF";
    public static String vnp_PayUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    
    // 2. Đường link VNPay sẽ "bẻ lái" trả khách hàng về sau khi quẹt thẻ xong
    // Lưu ý: Đảm bảo đúng cổng 9999 và tên project /SellingMerch của bạn
    public static String vnp_ReturnUrl = "http://localhost:9999/SellingMerch/vnpay-return";

    // Thuật toán băm bảo mật dữ liệu (Copy chuẩn theo tài liệu VNPay)
    public static String hmacSHA512(final String key, final String data) {
        try {
            if (key == null || data == null) {
                throw new NullPointerException();
            }
            final Mac hmac512 = Mac.getInstance("HmacSHA512");
            byte[] hmacKeyBytes = key.getBytes();
            final SecretKeySpec secretKey = new SecretKeySpec(hmacKeyBytes, "HmacSHA512");
            hmac512.init(secretKey);
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            byte[] result = hmac512.doFinal(dataBytes);
            StringBuilder sb = new StringBuilder(2 * result.length);
            for (byte b : result) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (Exception ex) {
            return "";
        }
    }

    // Hàm tạo mã Đơn hàng ngẫu nhiên (Ví dụ: 82736451)
    public static String getRandomNumber(int len) {
        Random rnd = new Random();
        String chars = "0123456789";
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }
}