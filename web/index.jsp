<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp" />

<main class="container mb-5">
    <div class="row row-cols-1 row-cols-md-3 g-5 justify-content-center">
        <c:forEach var="p" items="${products}">
            <div class="col" style="max-width: 350px;">
                <a href="product?id=${p.id}" class="text-decoration-none text-dark d-block h-100">
                    <div class="card product-card h-100 border-0 shadow-sm">
                        <img src="${p.image}" class="card-img-top product-img" alt="${p.name}">
                        <div class="card-body">
                            <h5 class="card-title fw-semibold mb-2">${p.name}</h5>
                            <p class="price-tag mb-0">
                                <fmt:formatNumber value="${p.price}" type="currency" currencySymbol="$"/>
                            </p>
                            </div>
                    </div>
                </a>
            </div>
        </c:forEach>
    </div>
</main>

<jsp:include page="footer.jsp" />