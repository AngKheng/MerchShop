<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản lý Đơn Hàng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        body { background-color: #f4f6f9; }
        .sidebar { height: 100vh; background-color: #343a40; padding-top: 20px; position: fixed; width: 250px; }
        .sidebar a { color: #c2c7d0; padding: 15px 20px; display: block; text-decoration: none; font-size: 1.1rem; }
        .sidebar a:hover, .sidebar a.active { background-color: #494e53; color: #fff; border-left: 4px solid #92c375; }
        .main-content { margin-left: 250px; padding: 30px; }
    </style>
</head>
<body>
    <div class="sidebar">
        <h3 class="text-white text-center mb-4 fw-bold">ADMIN PANEL</h3>
        <a href="admin"><i class="fas fa-box me-2"></i> Tổng quan Sản phẩm</a>
        <a href="adminorder" class="active"><i class="fas fa-shopping-cart me-2"></i> Quản lý Đơn hàng</a>
        <a href="admin-contacts"><i class="fas fa-envelope me-2"></i> Tin nhắn Liên hệ</a>
        <a href="home" class="mt-5 text-warning"><i class="fas fa-store me-2"></i> Về cửa hàng</a>
    </div>

    <div class="main-content">
        <h3 class="mb-4 fw-bold">Danh sách Đơn hàng</h3>
        <div class="card shadow-sm p-4 border-0 rounded-3">
            <table class="table table-bordered align-middle">
                <thead class="table-dark text-center">
                    <tr>
                        <th>Mã Đơn</th>
                        <th>Khách hàng</th>
                        <th>Sản phẩm đã đặt</th>
                        <th>Tổng tiền</th>
                        <th>Trạng thái</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="o" items="${listOrders}">
                        <tr>
                            <td class="text-center fw-bold">#${o.id}</td>
                            <td>
                                <b><i class="fas fa-user text-muted"></i> ${o.customerName}</b><br>
                                <small><i class="fas fa-phone text-muted"></i> ${o.phone}</small><br>
                                <small><i class="fas fa-map-marker-alt text-muted"></i> ${o.address}</small>
                            </td>
                            <td>
                                <ul class="list-unstyled mb-0">
                                    <c:forEach var="item" items="${o.details}">
                                        <li class="d-flex align-items-center mb-2 border-bottom pb-2">
                                            <img src="${item.productImage}" style="width: 45px; height: 45px; object-fit: cover; border-radius: 5px; margin-right: 15px;">
                                            <div>
                                                <span class="fw-semibold">${item.productName}</span><br>
                                                <small class="text-danger fw-bold">
                                                    <fmt:formatNumber value="${item.price}" type="currency" currencySymbol="$"/> x ${item.quantity}
                                                </small>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </td>
                            <td class="text-center text-danger fw-bold fs-5">
                                <fmt:formatNumber value="${o.totalAmount}" type="currency" currencySymbol="$"/>
                            </td>
                            <td class="text-center">
                                <span class="badge bg-success p-2">${o.status}</span>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>