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

            <div class="text-dark" style="font-size: 0.9rem; line-height: 1.6;">
                <p>This is a listing for the extra costs for The Hundred Line Gaku and Yugamu Dolls.</p>
                <p>Please add 1 [Extra Costs per plushie] for every plushie you have in your order.<br>
                Please add 1 [Yugamu Tax] for every Yugamu plushie in your order.</p>
                <p>This means that if you ordered all 4 plushies, with one Yugamu:<br>
                You will add 4 [Extra Costs per plushie] and 1 [Yugamu Tax]</p>
                <p>Please also make sure to write your previous order (or orders) number in the Notes so that I am able to link it back to your previous order! I will not ship out any orders until these extra costs have been paid ;w;!<br>
                Thank you for your understanding!</p>
            </div>
        </div>
    </div>
</main>

<jsp:include page="footer.jsp" />