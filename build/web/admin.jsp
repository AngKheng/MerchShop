<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - MerchShop</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        body { background-color: #f4f6f9; }
        .sidebar { height: 100vh; background-color: #343a40; padding-top: 20px; position: fixed; width: 250px; z-index: 1000; transition: 0.3s; }
        .sidebar a { color: #c2c7d0; padding: 15px 20px; display: block; text-decoration: none; font-size: 1.1rem; transition: 0.3s; }
        .sidebar a:hover, .sidebar a.active { background-color: #494e53; color: #fff; border-left: 4px solid #92c375; }
        .main-content { margin-left: 250px; padding: 20px; transition: 0.3s; }
        .card-stat { border-radius: 12px; border: none; transition: transform 0.2s; }
        .card-stat:hover { transform: translateY(-5px); }

        @media (max-width: 992px) {
            .sidebar { width: 100%; height: auto; position: relative; }
            .sidebar a { display: inline-block; padding: 10px 15px; font-size: 0.9rem; }
            .main-content { margin-left: 0; padding: 15px; }
            .sidebar .mt-5 { margin-top: 10px !important; }
        }

        @media (max-width: 768px) {
            .responsive-table thead { display: none; }
            .responsive-table tr { display: block; margin-bottom: 1.5rem; border: 1px solid #dee2e6; border-radius: 12px; background: #fff; padding: 10px; box-shadow: 0 2px 8px rgba(0,0,0,0.05); }
            .responsive-table td { display: flex; justify-content: space-between; align-items: center; border: none; padding: 8px 5px; border-bottom: 1px dashed #eee; text-align: right; }
            .responsive-table td::before { content: attr(data-label); font-weight: bold; text-align: left; font-size: 0.85rem; color: #666; }
            .responsive-table td:last-child { border-bottom: none; }
            .img-product { width: 80px !important; height: 80px !important; }
        }
    </style>
</head>
<body>
    <div class="sidebar">
        <h3 class="text-white text-center mb-4 fw-bold d-none d-lg-block">ADMIN PANEL</h3>
        <a href="admin" class="active"><i class="fas fa-box me-2"></i> Sản phẩm</a>
        <a href="adminorder"><i class="fas fa-shopping-cart me-2"></i> Đơn hàng</a>
        <a href="admin-contacts"><i class="fas fa-envelope me-2"></i> Tin nhắn</a>
        <a href="home" class="mt-lg-5 text-warning"><i class="fas fa-store me-2"></i> Về cửa hàng</a>
    </div>

    <div class="main-content">
        <h2 class="mb-4 fw-bold">Bảng điều khiển</h2>
        
        <div class="row g-3 mb-4">
            <div class="col-6 col-md-4">
                <div class="card card-stat bg-primary text-white p-3 p-md-4">
                    <small>Tổng Sản Phẩm</small>
                    <h2 class="fw-bold m-0">${totalProducts != null ? totalProducts : 0}</h2>
                </div>
            </div>
            <div class="col-6 col-md-4">
                <div class="card card-stat bg-success text-white p-3 p-md-4">
                    <small>Đơn Hàng</small>
                    <h2 class="fw-bold m-0">${totalOrders != null ? totalOrders : 0}</h2>
                </div>
            </div>
            <div class="col-12 col-md-4">
                <div class="card card-stat bg-warning text-dark p-3 p-md-4">
                    <small>Tin nhắn chờ</small>
                    <h2 class="fw-bold m-0">0</h2>
                </div>
            </div>
        </div>

        <div class="card border-0 shadow-sm p-3 p-md-4 rounded-3">
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h4 class="m-0 fw-bold">Danh sách Sản phẩm</h4>
                <a href="addproduct" class="btn btn-success fw-bold" style="background-color: #92c375; border: none;">
                    <i class="fas fa-plus-circle"></i> <span class="d-none d-sm-inline">Thêm mới</span>
                </a>
            </div>
            
            <table class="table align-middle responsive-table">
                <thead class="table-dark text-center">
                    <tr>
                        <th>Hình ảnh</th>
                        <th>Tên sản phẩm</th>
                        <th>Giá</th>
                        <th>Kho</th>
                        <th>Trạng thái</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="p" items="${listP}">
                        <tr>
                            <td data-label="Hình ảnh" class="text-center">
                                <img src="${p.image}" class="img-product" style="width: 50px; height: 50px; object-fit: cover; border-radius: 8px;">
                            </td>
                            <td data-label="Tên SP" class="fw-bold">${p.name} <br><span class="badge bg-light text-dark border fw-normal" style="font-size: 0.7rem;">${p.type}</span></td>
                            <td data-label="Giá">
                                <c:choose>
                                    <c:when test="${p.salePrice > 0}">
                                        <span class="text-danger fw-bold">$${p.salePrice}</span><br>
                                        <small class="text-decoration-line-through text-muted">$${p.price}</small>
                                    </c:when>
                                    <c:otherwise><b>$${p.price}</b></c:otherwise>
                                </c:choose>
                            </td>
                            <td data-label="Kho" class="fw-bold ${p.quantity <= 10 ? 'text-danger' : 'text-success'}">${p.quantity}</td>
                            <td data-label="Trạng thái">
                                <c:choose>
                                    <c:when test="${p.soldOut || p.quantity <= 0}">
                                        <span class="badge bg-danger">Sold Out</span>
                                    </c:when>
                                    <c:otherwise><span class="badge bg-success">Còn hàng</span></c:otherwise>
                                </c:choose>
                            </td>
                            <td data-label="Hành động" class="text-center">
                                <div class="btn-group">
                                    <a href="loadproduct?id=${p.id}" class="btn btn-sm btn-outline-primary"><i class="fas fa-edit"></i></a>
                                    <a href="deleteproduct?id=${p.id}" class="btn btn-sm btn-outline-danger" onclick="return confirm('Xóa sản phẩm này?');"><i class="fas fa-trash"></i></a>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>