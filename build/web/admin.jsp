<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        
        <a href="admin" class="active"><i class="fas fa-box me-2"></i> Tổng quan Sản phẩm</a>
        
        <a href="adminorder"><i class="fas fa-shopping-cart me-2"></i> Quản lý Đơn hàng</a>
        
        <a href="admin-contacts"><i class="fas fa-envelope me-2"></i> Tin nhắn Liên hệ</a>
        
        <a href="home" class="mt-5 text-warning"><i class="fas fa-store me-2"></i> Về cửa hàng</a>
    </div>

    <div class="main-content">
        <h2 class="mb-4">Bảng điều khiển (Dashboard)</h2>
<div class="row mb-5">
            <div class="col-md-4">
                <div class="card card-stat bg-primary text-white p-4 shadow-sm">
                    <h4>Tổng Sản Phẩm</h4>
                    <h2 class="fw-bold">${totalProducts != null ? totalProducts : 0}</h2>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card card-stat bg-success text-white p-4 shadow-sm">
                    <h4>Đơn Hàng</h4>
                    <h2 class="fw-bold">${totalOrders != null ? totalOrders : 0}</h2>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card card-stat bg-warning text-dark p-4 shadow-sm">
                    <h4>Tin nhắn chờ</h4>
                    <h2>0</h2> </div>
            </div>
        </div>
        <div class="card card-stat p-4">
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h4 class="m-0 text-dark fw-bold">Danh sách Sản phẩm</h4>
                <a href="addproduct" class="btn btn-success" style="background-color: #92c375; border: none;">
                    <i class="fas fa-plus-circle me-1"></i> Thêm mới
                </a>
            </div>
            
            <div class="table-responsive">
                <table class="table table-hover align-middle text-center">
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Hình ảnh</th>
                            <th class="text-start">Tên sản phẩm</th>
                            <th>Phân loại</th>
                            <th>Giá gốc</th>
                            <th>Giá Sale</th>
                            <th>Kho</th>
                            <th>Trạng thái</th>
                            <th>Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="p" items="${listP}">
                            <tr>
                                <td>#${p.id}</td>
                                <td>
                                    <img src="${p.image}" alt="img" style="width: 55px; height: 55px; object-fit: cover; border-radius: 8px; border: 1px solid #ddd;">
                                </td>
                                <td class="text-start fw-bold">${p.name}</td>
                                <td><span class="badge bg-secondary">${p.type}</span></td>
                                <td class="${p.salePrice > 0 ? 'text-decoration-line-through text-muted' : 'fw-bold'}">
                                    <fmt:formatNumber value="${p.price}" type="currency" currencySymbol="$"/>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${p.salePrice > 0}">
                                            <span class="text-danger fw-bold"><fmt:formatNumber value="${p.salePrice}" type="currency" currencySymbol="$"/></span>
                                        </c:when>
                                        <c:otherwise><span class="text-muted">-</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td class="fw-bold ${p.quantity <= 10 ? 'text-danger' : 'text-success'}">${p.quantity}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${p.soldOut || p.quantity <= 0}">
                                            <span class="badge bg-danger p-2">Sold Out</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge bg-success p-2">Còn hàng</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <a href="loadproduct?id=${p.id}" class="btn btn-sm btn-outline-primary" title="Sửa"><i class="fas fa-edit"></i></a>
                                    <a href="deleteproduct?id=${p.id}" class="btn btn-sm btn-outline-danger" title="Xóa" onclick="return confirm('Bạn có chắc chắn muốn xóa sản phẩm này vĩnh viễn không?');">
                                        <i class="fas fa-trash"></i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
