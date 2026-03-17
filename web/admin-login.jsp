<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Admin Login - MerchShop</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #343a40; display: flex; align-items: center; justify-content: center; height: 100vh; }
        .login-card { background: white; padding: 40px; border-radius: 10px; box-shadow: 0 10px 25px rgba(0,0,0,0.2); width: 100%; max-width: 400px; }
    </style>
</head>
<body>
    <div class="login-card">
        <h3 class="text-center fw-bold mb-4">ADMIN LOGIN</h3>
        
        <c:if test="${not empty error}">
            <div class="alert alert-danger text-center p-2">${error}</div>
        </c:if>
        
        <form action="admin-login" method="post">
            <div class="mb-3">
                <label class="form-label fw-bold">Tài khoản</label>
                <input type="text" name="username" class="form-control" required placeholder="Nhập tài khoản...">
            </div>
            <div class="mb-4">
                <label class="form-label fw-bold">Mật khẩu</label>
                <input type="password" name="password" class="form-control" required placeholder="Nhập mật khẩu...">
            </div>
            <button type="submit" class="btn btn-dark w-100 py-2 fw-bold">ĐĂNG NHẬP</button>
            <div class="text-center mt-3">
                <a href="home" class="text-muted text-decoration-none small"><i class="fas fa-arrow-left"></i> Quay lại cửa hàng</a>
            </div>
        </form>
    </div>
</body>
</html>