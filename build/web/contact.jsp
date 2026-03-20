<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp" />

<main class="container my-5" style="max-width: 600px;"> <div class="text-center mb-4">
        <h2 class="fw-normal text-dark mb-3">Contact</h2>
        <p class="text-muted" style="font-size: 0.95rem;">
            For any inquiries, you can e-mail me at mints-s@hotmail.com or message me at redbeersake on twitter!
        </p>
    </div>

    <c:if test="${not empty successMessage}">
        <div class="alert alert-success text-center fw-bold shadow-sm">
            ${successMessage}
        </div>
    </c:if>

    <form action="contact" method="post">
        <div class="mb-3">
            <label for="name" class="form-label fw-bold" style="font-size: 0.9rem; color: #333;">Name</label>
            <input type="text" class="form-control bg-transparent" id="name" name="name" required style="border: 1px solid #b5b5b5; border-radius: 4px;">
        </div>
        
        <div class="mb-3">
            <label for="email" class="form-label fw-bold" style="font-size: 0.9rem; color: #333;">Email</label>
            <input type="email" class="form-control bg-transparent" id="email" name="email" required style="border: 1px solid #b5b5b5; border-radius: 4px;">
        </div>
        
        <div class="mb-3">
            <label for="subject" class="form-label fw-bold" style="font-size: 0.9rem; color: #333;">Subject</label>
            <input type="text" class="form-control bg-transparent" id="subject" name="subject" required style="border: 1px solid #b5b5b5; border-radius: 4px;">
        </div>
        
        <div class="mb-4">
            <label for="message" class="form-label fw-bold" style="font-size: 0.9rem; color: #333;">Message</label>
            <textarea class="form-control bg-transparent" id="message" name="message" rows="6" required style="border: 1px solid #b5b5b5; border-radius: 4px;"></textarea>
        </div>
        
        <div class="text-center mb-5">
            <button type="submit" class="btn text-white fw-bold py-2 shadow-sm" style="background-color: #92c375; border: none; font-size: 1rem; width: 220px; border-radius: 4px;">
                Send message
            </button>
        </div>
    </form>

    <div class="text-center text-muted mt-4" style="font-size: 0.85rem;">
        Protected by reCAPTCHA. Google's <a href="#" class="text-muted text-decoration-underline">Privacy Policy</a> and <a href="#" class="text-muted text-decoration-underline">Terms of Service</a> apply.
    </div>

</main>

<jsp:include page="footer.jsp" />