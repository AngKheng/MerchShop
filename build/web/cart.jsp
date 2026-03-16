<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Giỏ hàng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* CSS để chỉnh kích thước ảnh trong giỏ hàng cho gọn gàng */
        .cart-img {
            width: 80px;
            height: 80px;
            object-fit: contain;
            background-color: #f8f9fa;
            border-radius: 8px;
            padding: 5px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .qty-btn {
            width: 35px;
            font-weight: bold;
        }
    </style>
</head>
<body class="bg-light">
    <nav class="navbar navbar-expand-lg navbar-light bg-light mb-4 shadow-sm">
        <div class="container">
            <a class="navbar-brand fw-bold" href="home">Merch Của Bạn</a>
            <div class="ms-auto">
                <a href="home" class="btn btn-outline-primary">Tiếp tục mua sắm</a>
            </div>
        </div>
    </nav>

    <div class="container mt-4 mb-5 bg-white p-4 rounded shadow-sm">
        <h2 class="mb-4 text-primary fw-bold">Giỏ hàng của bạn</h2>
        
        <c:if test="${not empty message}">
            <div class="alert alert-success">${message}</div>
        </c:if>

        <c:choose>
            <c:when test="${empty cart}">
                <div class="alert alert-info text-center py-5">
                    <h5>Giỏ hàng của bạn đang trống!</h5>
                    <a href="home" class="btn btn-primary mt-3">Quay lại mua sắm</a>
                </div>
            </c:when>
            
            <c:otherwise>
                <div class="table-responsive">
                    <table class="table align-middle text-center table-hover">
                        <thead class="table-light">
                            <tr>
                                <th>Hình ảnh</th>
                                <th>Sản phẩm</th>
                                <th>Giá</th>
                                <th>Số lượng</th>
                                <th>Tổng</th>
                                <th>Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="total" value="0"/>
                            <c:forEach var="item" items="${cart}">
                                <tr>
                                    <td>
                                        <img src="${item.product.image}" alt="${item.product.name}" class="cart-img">
                                    </td>
                                    
                                    <td class="fw-semibold text-start">${item.product.name}</td>
                                    
                                    <td><fmt:formatNumber value="${item.product.price}" type="currency" currencySymbol="$"/></td>
                                    
                                    <td>
                                        <div class="d-flex justify-content-center align-items-center">
                                            <form action="updatecart" method="post" class="m-0">
                                                <input type="hidden" name="id" value="${item.product.id}">
                                                <input type="hidden" name="action" value="decrease">
                                                <button type="submit" class="btn btn-outline-secondary btn-sm qty-btn">-</button>
                                            </form>

                                            <span class="mx-3 fs-5">${item.quantity}</span>

                                            <form action="updatecart" method="post" class="m-0">
                                                <input type="hidden" name="id" value="${item.product.id}">
                                                <input type="hidden" name="action" value="increase">
                                                <button type="submit" class="btn btn-outline-secondary btn-sm qty-btn">+</button>
                                            </form>
                                        </div>
                                    </td>
                                    
                                    <td class="text-danger fw-bold">
                                        <fmt:formatNumber value="${item.totalPrice}" type="currency" currencySymbol="$"/>
                                    </td>
                                    
                                    <td>
                                        <form action="removefromcart" method="post" class="m-0">
                                            <input type="hidden" name="id" value="${item.product.id}">
                                            <button type="submit" class="btn btn-danger btn-sm px-3">Xóa</button>
                                        </form>
                                    </td>
                                </tr>
                                <c:set var="total" value="${total + item.totalPrice}"/>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <hr>

                <div class="row align-items-center mt-4">
                    <div class="col-md-6">
                        <h5 class="mb-0">Số sản phẩm đã chọn: <span class="badge bg-secondary">${cart.size()}</span></h5>
                    </div>
                    <div class="col-md-6 text-end">
                        <h4 class="mb-3">Tổng tiền: <span class="text-danger fw-bold"><fmt:formatNumber value="${total}" type="currency" currencySymbol="$"/></span></h4>
                        <form action="checkout" method="post">
                            <button type="submit" class="btn btn-success btn-lg px-5 shadow-sm">Thanh toán ngay</button>
                        </form>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>