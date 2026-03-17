<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MERCH SHOP</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    
    <style>
        /* Màu nền be nhạt giống thiết kế của bạn */
        body {
            background-color: #f6f5f0; 
            font-family: Arial, sans-serif;
        }

        /* ----- HEADER STYLES ----- */
        .top-nav {
            padding: 1.5rem 2rem;
        }
        .top-nav a {
            color: #333;
            text-decoration: none;
            margin-right: 1.5rem;
            font-size: 0.95rem;
        }
        .top-nav a:hover { color: #000; }
        
        .logo-container {
            text-align: center;
            margin: 2rem 0;
        }
        .logo-container img {
            max-height: 180px; /* Bạn có thể chỉnh lại cho vừa logo thật */
        }
        
        .category-nav {
            text-align: center;
            margin-bottom: 3rem;
            display: flex;
            justify-content: center;
            flex-wrap: wrap;
            gap: 1.5rem;
        }
        .category-nav a {
            color: #555;
            text-decoration: none;
            font-size: 0.9rem;
        }
        .category-nav a:hover {
            color: #000;
            text-decoration: underline;
        }

        /* ----- PRODUCT CARD STYLES (Giữ nguyên cấu hình vuông cũ) ----- */
        .product-card {
            transition: box-shadow 0.3s ease;
            border-radius: 12px;
            overflow: hidden;
            background-color: #fff;
        }
        .product-card:hover { box-shadow: 0 10px 20px rgba(0,0,0,0.12); }
        .product-img {
            width: 100%;
            aspect-ratio: 1 / 1;
            object-fit: cover;
            padding: 0;
        }
        .card-body {
            text-align: center;
            padding: 1.5rem;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }
        .card-title {
            transition: color 0.3s ease;
            margin-bottom: 0.5rem;
        }
        .product-card:hover .card-title { color: #e63946; }
        .price-tag {
            font-size: 1.3rem;
            font-weight: bold;
            color: #e63946;
            margin-top: auto;
            margin-bottom: 1rem;
        }
    </style>
</head>
<body>

    <c:set var="totalQuantity" value="0" />
    <c:if test="${not empty sessionScope.cart}">
        <c:forEach var="item" items="${sessionScope.cart}">
            <c:set var="totalQuantity" value="${totalQuantity + item.quantity}" />
        </c:forEach>
    </c:if>

    <header class="container-fluid p-0">
        <div class="d-flex justify-content-between align-items-center top-nav">
            <div>
                <a href="home">Product</a>
                <a href="contact">Contact</a>
                
            </div>
            <div>
                <a href="#"><i class="fas fa-search me-1"></i></a>
                <a href="cart" class="ms-3">
                    <i class="fas fa-shopping-cart me-1"></i> ${totalQuantity}
                </a>
            </div>
        </div>

        <div class="logo-container">
            <img src="image/your-logo.png" alt="MERCH SHOP" 
                 onerror="this.outerHTML='<h1 class=\'fw-bold\' style=\'color: #4CAF50;\'>MERCH SHOP</h1>'">
        </div>

        <div class="text-center">
            <h4 class="mb-4 text-dark" style="font-weight: 400;">Products</h4>
            <div class="category-nav">
                <a href="home?type=All" style="${(empty selectedCategory or selectedCategory eq 'All') ? 'color: #000; text-decoration: underline; font-weight: bold;' : ''}">All</a>
                <a href="home?type=FFXIV" style="${selectedCategory eq 'FFXIV' ? 'color: #000; text-decoration: underline; font-weight: bold;' : ''}">FFXIV</a>
                <a href="home?type=Charms" style="${selectedCategory eq 'Charms' ? 'color: #000; text-decoration: underline; font-weight: bold;' : ''}">Charms</a>
                <a href="home?type=Standees" style="${selectedCategory eq 'Standees' ? 'color: #000; text-decoration: underline; font-weight: bold;' : ''}">Standees</a>
                <a href="home?type=Books" style="${selectedCategory eq 'Books' ? 'color: #000; text-decoration: underline; font-weight: bold;' : ''}">Books</a>
                <a href="home?type=Dungeon Meshi" style="${selectedCategory eq 'Dungeon Meshi' ? 'color: #000; text-decoration: underline; font-weight: bold;' : ''}">Dungeon Meshi</a>
                <a href="home?type=Wuthering Waves" style="${selectedCategory eq 'Wuthering Waves' ? 'color: #000; text-decoration: underline; font-weight: bold;' : ''}">Wuthering Waves</a>
                <a href="home?type=The Hundred Line" style="${selectedCategory eq 'The Hundred Line' ? 'color: #000; text-decoration: underline; font-weight: bold;' : ''}">The Hundred Line</a>
                <a href="home?type=Others" style="${selectedCategory eq 'Others' ? 'color: #000; text-decoration: underline; font-weight: bold;' : ''}">Others</a>
            </div>
        </div>
    </header>