<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Giỏ hàng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>Giỏ hàng của bạn</h2>
        <c:if test="${not empty message}">
            <div class="alert alert-success">${message}</div>
        </c:if>

        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Sản phẩm</th>
                    <th>Giá</th>
                    <th>Số lượng</th>
                    <th>Tổng</th>
                    <th>Xóa</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="total" value="0"/>
                <c:forEach var="item" items="${cart}">
                    <tr>
                        <td>${item.product.name}</td>
                        <td>$${item.product.price}</td>
                        <td>${item.quantity}</td>
                        <td>$${item.totalPrice}</td>
                        <td>
                            <form action="removefromcart" method="post">
                                <input type="hidden" name="id" value="${item.product.id}">
                                <button type="submit" class="btn btn-danger">Xóa</button>
                            </form>
                        </td>
                    </tr>
                    <c:set var="total" value="${total + item.totalPrice}"/>
                </c:forEach>
            </tbody>
        </table>

        <h4>Tổng tiền: <span class="text-danger">$${total}</span></h4>
        <h5>Số sản phẩm đã chọn: ${cart.size()} loại</h5>

        <c:if test="${not empty cart}">
            <form action="checkout" method="post">
                <button type="submit" class="btn btn-success btn-lg">Thanh toán ngay</button>
            </form>
        </c:if>
    </div>
</body>
</html>