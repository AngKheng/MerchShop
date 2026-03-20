<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý Đơn Hàng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        body { background-color: #f4f6f9; }
        .sidebar { height: 100vh; background-color: #343a40; padding-top: 20px; position: fixed; width: 250px; transition: 0.3s; z-index: 1000; }
        .sidebar a { color: #c2c7d0; padding: 15px 20px; display: block; text-decoration: none; font-size: 1.1rem; }
        .sidebar a:hover, .sidebar a.active { background-color: #494e53; color: #fff; border-left: 4px solid #92c375; }
        .main-content { margin-left: 250px; padding: 20px; transition: 0.3s; }

        /* Tối ưu cho Tablet và Mobile */
        @media (max-width: 992px) {
            .sidebar { width: 100%; height: auto; position: relative; }
            .sidebar a { display: inline-block; padding: 10px 15px; font-size: 0.9rem; }
            .main-content { margin-left: 0; padding: 15px; }
            .sidebar .mt-5 { margin-top: 10px !important; }
        }

        /* Biến Table thành Card trên Mobile */
        @media (max-width: 768px) {
            .responsive-table thead { display: none; } /* Ẩn tiêu đề bảng */
            .responsive-table, .responsive-table tbody, .responsive-table tr, .responsive-table td { display: block; width: 100%; }
            .responsive-table tr { margin-bottom: 20px; border: 1px solid #dee2e6; border-radius: 10px; background: #fff; padding: 10px; box-shadow: 0 2px 5px rgba(0,0,0,0.05); }
            .responsive-table td { border: none; padding: 10px 5px; position: relative; text-align: left; border-bottom: 1px dashed #eee; }
            .responsive-table td:last-child { border-bottom: none; }
            
            /* Thêm tiêu đề giả cho từng ô */
            .responsive-table td::before { content: attr(data-label); font-weight: bold; display: block; color: #343a40; font-size: 0.8rem; text-transform: uppercase; margin-bottom: 5px; }
        }
    </style>
</head>
<body>
    <div class="sidebar">
        <h3 class="text-white text-center mb-4 fw-bold d-none d-lg-block">ADMIN PANEL</h3>
        <a href="admin"><i class="fas fa-box me-2"></i> Sản phẩm</a>
        <a href="adminorder" class="active"><i class="fas fa-shopping-cart me-2"></i> Đơn hàng</a>
        <a href="admin-contacts"><i class="fas fa-envelope me-2"></i> Tin nhắn</a>
        <a href="home" class="mt-lg-5 text-warning"><i class="fas fa-store me-2"></i> Về cửa hàng</a>
    </div>

    <div class="main-content">
        <h3 class="mb-4 fw-bold mt-2">Danh sách Đơn hàng</h3>
        <div class="card shadow-sm border-0 rounded-3 overflow-hidden">
            <div class="table-responsive-md">
                <table class="table align-middle mb-0 responsive-table">
                    <thead class="table-dark text-center">
                        <tr>
                            <th>Mã Đơn</th>
                            <th>Khách hàng</th>
                            <th>Sản phẩm</th>
                            <th>Tổng tiền</th>
                            <th>Trạng thái</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="o" items="${listOrders}">
                            <tr>
                                <td data-label="Mã Đơn" class="text-lg-center fw-bold text-primary">#${o.id}</td>
                                <td data-label="Khách hàng">
                                    <div class="ps-lg-2">
                                        <b>${o.customerName}</b><br>
                                        <small class="text-muted"><i class="fas fa-phone"></i> ${o.phone}</small><br>
                                        <small class="text-muted"><i class="fas fa-map-marker-alt"></i> ${o.address}</small>
                                    </div>
                                </td>
                                <td data-label="Sản phẩm">
                                    <c:forEach var="item" items="${o.details}">
                                        <div class="d-flex align-items-center mb-2">
                                            <img src="${item.productImage}" style="width: 40px; height: 40px; object-fit: cover; border-radius: 5px;" class="me-2">
                                            <div>
                                                <small class="fw-bold">${item.productName}</small><br>
                                                <small class="text-danger">$${item.price} x ${item.quantity}</small>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </td>
                                <td data-label="Tổng tiền" class="text-lg-center text-danger fw-bold fs-5">
                                    <fmt:formatNumber value="${o.totalAmount}" type="currency" currencySymbol="$"/>
                                </td>
                                <td data-label="Trạng thái" class="text-lg-center">
                                    <span class="badge bg-success w-100 w-lg-auto">${o.status}</span>
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