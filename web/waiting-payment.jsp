<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Thanh toán đơn hàng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="card shadow mx-auto" style="max-width: 500px; border-radius: 20px;">
            <div class="card-body text-center p-5">
                <h3 class="fw-bold">Thanh toán đơn hàng #${lastOrderId}</h3>
                <p class="text-muted">Vui lòng quét mã QR dưới đây để hoàn tất</p>
                
                <div class="my-4">
                    <img src="https://img.vietqr.io/image/MB-2348691732779-compact2.png?amount=${lastAmount}&addInfo=DH${lastOrderId}&accountName=DOAN%20VU%20NGOC%20KHANG" 
                         class="img-fluid border p-2 rounded-4" style="width: 280px;">
                </div>

                <div class="alert alert-warning">
                    Nội dung chuyển khoản: <strong>DH${lastOrderId}</strong>
                </div>

                <div class="d-flex align-items-center justify-content-center">
                    <div class="spinner-border spinner-border-sm text-primary me-2"></div>
                    <span>Đang chờ hệ thống xác nhận thanh toán...</span>
                </div>
            </div>
        </div>
    </div>

    <script>
        // Cứ 3 giây lại kiểm tra trạng thái đơn hàng 1 lần
        // Bạn cần viết thêm 1 Servlet nhỏ checkStatus đơn giản trả về text
        setInterval(function() {
            fetch('check-status?id=${lastOrderId}')
                .then(res => res.text())
                .then(data => {
                    if(data === "success") {
                        alert("Thanh toán thành công! Đang chuyển hướng...");
                        window.location.href = "home";
                    }
                });
        }, 3000);
    </script>
</body>
</html>