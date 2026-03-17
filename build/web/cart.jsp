<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp" />

<style>
    .cart-img {
        width: 80px;
        height: 80px;
        object-fit: cover; /* Cắt vuông vức giống trang chủ */
        border-radius: 8px;
    }
    .qty-btn {
        width: 35px;
        font-weight: bold;
    }
</style>

<main class="container mt-4 mb-5 p-4 bg-white rounded shadow-sm" style="max-width: 1000px;">
    <h2 class="mb-4 fw-bold" style="color: #333;">Giỏ hàng của bạn</h2>
    
    <c:if test="${not empty message}">
        <div class="alert alert-success">${message}</div>
    </c:if>

    <c:choose>
        <c:when test="${empty cart}">
            <div class="alert alert-light text-center py-5 border">
                <h5 class="text-muted mb-4">Giỏ hàng của bạn đang trống!</h5>
                <a href="home" class="btn btn-dark px-4">Tiếp tục mua sắm</a>
            </div>
        </c:when>
        
        <c:otherwise>
            <div class="table-responsive">
                <table class="table align-middle text-center table-hover border-top">
                    <thead class="table-light">
                        <tr>
                            <th>Hình ảnh</th>
                            <th class="text-start">Sản phẩm</th>
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
                                    <img src="${item.product.image}" alt="${item.product.name}" class="cart-img border">
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

            <hr class="my-4">

            <div class="row align-items-center">
                <div class="col-md-6 text-muted">
                    <h6 class="mb-0">Số sản phẩm đã chọn: <span class="badge bg-secondary">${cart.size()}</span></h6>
                </div>
                <div class="col-md-6 text-end">
                    <h4 class="mb-3 text-dark">Tổng tiền: <span class="text-danger fw-bold"><fmt:formatNumber value="${total}" type="currency" currencySymbol="$"/></span></h4>
                    <a href="checkout.jsp" class="btn btn-success btn-lg px-5 shadow-sm" style="background-color: #92c375; border: none; text-decoration: none;">Thanh toán ngay</a>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</main>

<jsp:include page="footer.jsp" />