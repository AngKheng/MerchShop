<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp" />

<main class="container mb-5">
    <div class="row row-cols-1 row-cols-md-3 g-5 justify-content-center">
        <c:forEach var="p" items="${products}">
            <div class="col" style="max-width: 350px;">
                <a href="product?id=${p.id}" class="text-decoration-none text-dark d-block h-100">
                    <div class="card product-card h-100 border-0 shadow-sm position-relative">
                        
                        <c:choose>
                            <c:when test="${p.soldOut || p.quantity <= 0}">
                                <span class="badge bg-danger position-absolute shadow-sm" style="top: 15px; right: 15px; font-size: 0.85rem; z-index: 2;">Sold Out</span>
                            </c:when>
                            <c:when test="${p.salePrice > 0}">
                                <span class="badge bg-warning text-dark position-absolute shadow-sm" style="top: 15px; right: 15px; font-size: 0.85rem; z-index: 2;">Sale</span>
                            </c:when>
                        </c:choose>

                        <img src="${p.image}" class="card-img-top product-img" alt="${p.name}">
                        
                        <div class="card-body">
                            <h5 class="card-title fw-semibold mb-2">${p.name}</h5>
                            
                            <p class="price-tag mb-0">
                                <c:choose>
                                    <c:when test="${p.salePrice > 0}">
                                        <span class="text-muted text-decoration-line-through me-2" style="font-size: 0.95rem;">
                                            <fmt:formatNumber value="${p.price}" type="currency" currencySymbol="$"/>
                                        </span>
                                        <span class="text-danger fw-bold fs-5">
                                            <fmt:formatNumber value="${p.salePrice}" type="currency" currencySymbol="$"/>
                                        </span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="fw-bold fs-5">
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