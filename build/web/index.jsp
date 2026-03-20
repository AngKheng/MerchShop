<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp" />

<style>
    .product-img {
        height: 250px;
        object-fit: cover;
        width: 100%;
    }
    .product-card {
        transition: transform 0.2s;
    }
    .product-card:hover {
        transform: translateY(-5px);
    }
    @media (max-width: 576px) {
        .product-img { height: 200px; }
        .card-title { font-size: 1rem; }
    }
</style>

<main class="container my-4">
    <div class="row row-cols-2 row-cols-md-3 row-cols-lg-4 g-3 g-lg-4 justify-content-center">
        <c:forEach var="p" items="${products}">
            <div class="col">
                <a href="product?id=${p.id}" class="text-decoration-none text-dark d-block h-100">
                    <div class="card product-card h-100 border-0 shadow-sm position-relative overflow-hidden">
                        
                        <c:choose>
                            <c:when test="${p.soldOut || p.quantity <= 0}">
                                <span class="badge bg-danger position-absolute shadow-sm" style="top: 10px; right: 10px; font-size: 0.75rem; z-index: 2;">Sold Out</span>
                            </c:when>
                            <c:when test="${p.salePrice > 0}">
                                <span class="badge bg-warning text-dark position-absolute shadow-sm" style="top: 10px; right: 10px; font-size: 0.75rem; z-index: 2;">Sale</span>
                            </c:when>
                        </c:choose>

                        <img src="${p.image}" class="card-img-top product-img" alt="${p.name}">
                        
                        <div class="card-body p-2 p-md-3">
                            <h6 class="card-title fw-semibold mb-1 text-truncate">${p.name}</h6>
                            
                            <p class="price-tag mb-0">
                                <c:choose>
                                    <c:when test="${p.salePrice > 0}">
                                        <span class="text-muted text-decoration-line-through small me-1">
                                            <fmt:formatNumber value="${p.price}" type="currency" currencySymbol="$"/>
                                        </span>
                                        <span class="text-danger fw-bold">
                                            <fmt:formatNumber value="${p.salePrice}" type="currency" currencySymbol="$"/>
                                        </span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="fw-bold text-dark">
                                            <fmt:formatNumber value="${p.price}" type="currency" currencySymbol="$"/>
                                        </span>
                                    </c:otherwise>
                                </c:choose>
                            </p>
                        </div>
                    </div>
                </a>
            </div>
        </c:forEach>
    </div>
</main>

<jsp:include page="footer.jsp" />