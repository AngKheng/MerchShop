<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Merch Của Bạn</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<style>
    /* 1. Thiết lập cơ bản cho thẻ sản phẩm */
    .product-card {
        transition: box-shadow 0.3s ease; /* Hiệu ứng đổ bóng nhẹ mượt */
        border-radius: 12px; /* Bo góc đẹp hơn */
        overflow: hidden; /* Cần thiết để đảm bảo bo góc và hình vuông */
    }

    /* Bóng đổ nhẹ khi hover vào thẻ sản phẩm */
    .product-card:hover {
        box-shadow: 0 10px 20px rgba(0,0,0,0.12);
    }

    /* 2. Thiết lập cho ảnh sản phẩm hình vuông */
    .product-img {
        width: 100%;
        aspect-ratio: 1 / 1; /* Tạo khung hình vuông cố định */
        object-fit: cover; /* Cắt ảnh thay vì kéo giãn */
        background-color: #f8f9fa; /* Màu nền cho phần ảnh bị cắt nếu cần */
        padding: 0; /* Xóa padding để ảnh lấp đầy khung hình vuông */
    }
    
    /* 3. Phần text và giá tiền bên dưới */
    .card-body {
        text-align: center;
        padding: 1.5rem;
        display: flex;
        flex-direction: column;
        justify-content: space-between; /* Đẩy giá và nút xuống dưới cùng */
    }
    .card-title {
        transition: color 0.3s ease; /* Hiệu ứng đổi màu mượt */
        margin-bottom: 0.5rem;
    }
    
    /* Hiệu ứng hover mới: Đổi màu tiêu đề sản phẩm sang màu đỏ giống giá tiền */
    .product-card:hover .card-title {
        color: #e63946; /* Đổi sang màu đỏ */
    }

    .price-tag {
        font-size: 1.6rem;
        font-weight: 800;
        color: #e63946;
        margin-top: auto; /* Đẩy giá tiền lên trên nút bấm */
        margin-bottom: 1rem;
    }

    .btn-add-cart {
        font-weight: bold;
    }
</style>
</head>
<c:set var="totalQuantity" value="0" />
    <c:if test="${not empty sessionScope.cart}">
        <c:forEach var="item" items="${sessionScope.cart}">
            <c:set var="totalQuantity" value="${totalQuantity + item.quantity}" />
        </c:forEach>
    </c:if>

    <nav class="navbar navbar-expand-lg navbar-light bg-light mb-4 shadow-sm">
        <div class="container">
            <a class="navbar-brand fw-bold" href="home">Merch Shop</a>
            <div class="ms-auto">
                <a href="home" class="btn btn-outline-primary me-2">Trang chủ</a>
                <a href="cart" class="btn btn-outline-success position-relative">
                    Giỏ hàng
                    <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                        ${totalQuantity}
                    </span>
                </a>
            </div>
        </div>
    </nav>
<body class="bg-light">

    <div class="container my-5">
        <h1 class="text-center mb-5 fw-bold text-primary">Merch Shop</h1>

        <div class="row row-cols-1 row-cols-md-4 g-4">
<c:forEach var="p" items="${products}">
    <div class="col">
        <div class="card product-card h-100 border-0 shadow-sm">
            
            <img src="${p.image}" class="card-img-top product-img" alt="${p.name}">
            
            <div class="card-body">
                <h5 class="card-title fw-semibold mb-2">${p.name}</h5>
                <p class="price-tag mb-0">
                    <fmt:formatNumber value="${p.price}" type="currency" currencySymbol="$"/>
                </p>
                
                <form action="addtocart" method="post" class="mt-auto m-0">
                    <input type="hidden" name="id" value="${p.id}">
                    <button type="submit" class="btn btn-primary btn-add-cart w-100">
                        Thêm vào giỏ hàng
                    </button>
                </form>
            </div>
        </div>
    </div>
</c:forEach>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>