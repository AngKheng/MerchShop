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
        .product-card {
            transition: transform 0.2s, box-shadow 0.2s;
        }
        .product-card:hover {
            transform: translateY(-8px);
            box-shadow: 0 10px 20px rgba(0,0,0,0.15);
        }
        .product-img {
            height: 280px;
            object-fit: contain;
            background-color: #f8f9fa;
            padding: 15px;
        }
        .price-tag {
            font-size: 1.4rem;
            font-weight: bold;
            color: #e63946;
        }
        .card-body {
            text-align: center;
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
            <a class="navbar-brand fw-bold" href="home">Merch Của Bạn</a>
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
                            <p class="price-tag mb-3">
                                <fmt:formatNumber value="${p.price}" type="currency" currencySymbol="$"/>
                            </p>
                            <form action="addtocart" method="post">
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