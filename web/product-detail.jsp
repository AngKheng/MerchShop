<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp" />

<main class="container my-5" style="max-width: 1000px;">
    <div class="mb-4 text-muted" style="font-size: 0.9rem;">
        <a href="home?type=All" class="text-decoration-none text-muted">All</a> / 
        
        <a href="home?type=${not empty product.type ? product.type : 'Others'}" class="text-decoration-none text-muted fw-bold">
            ${not empty product.type ? product.type : 'Others'}
        </a>
    </div>

    <div class="row g-5">
        <div class="col-md-6">
            <div class="bg-white p-2 shadow-sm position-relative" style="border-radius: 4px;">
                <c:choose>
                    <c:when test="${product.soldOut || product.quantity <= 0}">
                        <span class="badge bg-danger position-absolute shadow-sm" style="top: 15px; right: 15px; font-size: 1rem; z-index: 2;">Sold Out</span>
                    </c:when>
                    <c:when test="${product.salePrice > 0}">
                        <span class="badge bg-warning text-dark position-absolute shadow-sm" style="top: 15px; right: 15px; font-size: 1rem; z-index: 2;">Sale</span>
                    </c:when>
                </c:choose>
                
                <img src="${product.image}" alt="${product.name}" class="img-fluid w-100" style="object-fit: contain;">
            </div>
        </div>

        <div class="col-md-6">
            <h2 class="fw-bold mb-2 text-uppercase" style="font-family: Arial, sans-serif;">${product.name}</h2>
            
            <h5 class="mb-4 text-dark">
                <c:choose>
                    <c:when test="${product.salePrice > 0}">
                        <span class="text-muted text-decoration-line-through me-3" style="font-size: 1.2rem;">
                            <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="$"/>
                        </span>
                        <span class="text-danger fw-bold fs-3">
                            <fmt:formatNumber value="${product.salePrice}" type="currency" currencySymbol="$"/>
                        </span>
                    </c:when>
                    <c:otherwise>
                        <span class="fw-bold fs-3 text-danger">
                            <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="$"/>
                        </span>
                    </c:otherwise>
                </c:choose>
            </h5>

            <c:choose>
                <c:when test="${product.soldOut || product.quantity <= 0}">
                    <div class="alert alert-danger text-center fw-bold mb-4">
                        <i class="fas fa-ban me-2"></i> Sản phẩm này hiện đang hết hàng!
                    </div>
                    <button type="button" class="btn w-100 py-2 fw-bold text-white shadow-sm" style="background-color: #ccc; border: none; font-size: 1rem; cursor: not-allowed;" disabled>
                        Sold Out
                    </button>
                </c:when>
                
                <c:otherwise>
                    <form action="addtocart" method="post" class="mb-5">
                        <input type="hidden" name="id" value="${product.id}">
                        
                        <div class="mb-4">
                            <label for="quantity" class="form-label text-muted fw-bold">Quantity</label>
                            <div class="d-flex align-items-center">
                                <input type="number" id="quantity" name="quantity" class="form-control me-3" value="1" min="1" max="${product.quantity}" style="max-width: 120px; background-color: #f6f5f0;">
                                <small class="text-muted">(Còn ${product.quantity} sản phẩm)</small>
                            </div>
                        </div>

                        <button type="submit" class="btn w-100 py-2 fw-bold text-white shadow-sm" style="background-color: #92c375; border: none; font-size: 1rem;">
                            Add to cart
                        </button>
                    </form>
                </c:otherwise>
            </c:choose>

            <div class="text-dark mt-4" style="font-size: 0.95rem; line-height: 1.6; white-space: pre-wrap; text-align: justify;">${product.description}</div>
        </div>
    </div>
</main>

<jsp:include page="footer.jsp" />