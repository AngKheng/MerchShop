<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard - MerchShop</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        body { background-color: #f4f6f9; }
        .sidebar {
            height: 100vh; background-color: #343a40;
            padding-top: 20px; position: fixed; width: 250px;
        }
        .sidebar a {
            color: #c2c7d0; padding: 15px 20px;
            display: block; text-decoration: none;
            font-size: 1.1rem; transition: 0.3s;
        }
        .sidebar a:hover, .sidebar a.active {
            background-color: #494e53; color: #fff;
            border-left: 4px solid #92c375;
        }
        .main-content { margin-left: 250px; padding: 30px; }
        .card-stat { border-radius: 10px; border: none; box-shadow: 0 0 15px rgba(0,0,0,0.05); }
    </style>
</head>
<body>
    <div class="sidebar">
        <h3 class="text-white text-center mb-4 fw-bold">ADMIN PANEL</h3>
        <a href="admin" class="active"><i class="fas fa-tachometer-alt me-2"></i> Tổng quan</a>
        
        <a href="addproduct"><i class="fas fa-plus-circle me-2"></i> Thêm Sản phẩm</a>
        
        <a href="#"><i class="fas fa-box me-2"></i> Quản lý Sản phẩm</a>
        <a href="#"><i class="fas fa-shopping-cart me-2"></i> Quản lý Đơn hàng</a>
        <a href="#"><i class="fas fa-envelope me-2"></i> Tin nhắn Liên hệ</a>
        
        <a href="home" class="mt-5 text-warning"><i class="fas fa-store me-2"></i> Về cửa hàng</a>
    </div>

    <div class="main-content">
        <h2 class="mb-4">Bảng điều khiển (Dashboard)</h2>
        <div class="row mb-5">
            <div class="col-md-4">
                <div class="card card-stat bg-primary text-white p-4">
                    <h4>Tổng Sản Phẩm</h4><h2>0</h2>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card card-stat bg-success text-white p-4">
                    <h4>Đơn Hàng Mới</h4><h2>0</h2>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card card-stat bg-warning text-dark p-4">
                    <h4>Tin nhắn chờ</h4><h2>0</h2>
                </div>
            </div>
        </div>
        <div class="card card-stat p-4">
            <h4 class="mb-3">Chào mừng bạn đến với trang Quản trị!</h4>
            <p class="text-muted">Giao diện đã sẵn sàng. Bạn có thể bấm vào "Thêm Sản phẩm" ở menu bên trái để trải nghiệm.</p>
        </div>
    </div>
</body>
</html>
