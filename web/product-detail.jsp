<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp" />

<main class="container my-5" style="max-width: 1000px;">
    <div class="mb-4 text-muted" style="font-size: 0.9rem;">
        <a href="home" class="text-decoration-none text-muted">DOLLS EXTRA COSTS</a> / Others
    </div>

    <div class="row g-5">
        <div class="col-md-6">
            <div class="bg-white p-2 shadow-sm" style="border-radius: 4px;">
                <img src="${product.image}" alt="${product.name}" class="img-fluid w-100" style="object-fit: contain;">
            </div>
        </div>

        <div class="col-md-6">
            <h2 class="fw-bold mb-2 text-uppercase" style="font-family: Arial, sans-serif;">${product.name}</h2>
            <h5 class="mb-4 text-dark">
                <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="$"/>
            </h5>

            <form action="addtocart" method="post" class="mb-5">
                <input type="hidden" name="id" value="${product.id}">
                
                <div class="mb-4">
                    <label for="quantity" class="form-label text-muted fw-bold">Quantity</label>
                    <input type="number" id="quantity" name="quantity" class="form-control" value="1" min="1" style="max-width: 150px; background-color: #f6f5f0;">
                </div>

                <button type="submit" class="btn w-100 py-2 fw-bold text-white shadow-sm" style="background-color: #92c375; border: none; font-size: 1rem;">
                    Add to cart
                </button>
            </form>

<div class="text-dark mt-4" style="font-size: 0.95rem; line-height: 1.6; white-space: pre-wrap; text-align: justify;">${product.description}</div>
        </div>
    </div>
</main>

<jsp:include page="footer.jsp" />