<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thông tin giao hàng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body style="background-color: #f6f5f0;">
    <div class="container mt-5" style="max-width: 600px;">
        <h3 class="mb-4">Thông tin giao hàng</h3>
        
        <form action="checkout" method="post" class="bg-white p-4 rounded shadow-sm">
            <div class="mb-3">
                <label>Họ và tên:</label>
                <input type="text" name="cusName" class="form-control" required>
            </div>
            <div class="mb-3">
                <label>Số điện thoại:</label>
                <input type="text" name="cusPhone" class="form-control" required>
            </div>
            <div class="mb-3">
                <label>Địa chỉ nhận hàng:</label>
                <input type="text" name="cusAddress" class="form-control" required>
            </div>
            <div class="mb-4">
                <label>Email:</label>
                <input type="email" name="cusEmail" class="form-control" required>
            </div>
            
            <button type="submit" class="btn btn-dark w-100">Tiếp tục thanh toán VNPay</button>
        </form>
    </div>
</body>
</html>