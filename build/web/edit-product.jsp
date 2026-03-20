<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Sửa Sản Phẩm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body style="background-color: #f4f6f9;">
    <div class="container mt-5" style="max-width: 600px;">
        <div class="card shadow p-4">
            <h3 class="mb-4 fw-bold">Chỉnh sửa Sản phẩm #${detail.id}</h3>
            
            <form action="editproduct" method="post" enctype="multipart/form-data">
                <input type="hidden" name="id" value="${detail.id}">
                <input type="hidden" name="oldImage" value="${detail.image}">
                
                <div class="mb-3">
                    <label>Tên sản phẩm:</label>
                    <input type="text" name="name" class="form-control" value="${detail.name}" required>
                </div>
                
                <div class="row mb-3">
                    <div class="col-6">
                        <label>Giá gốc ($):</label>
                        <input type="number" step="0.01" name="price" class="form-control" value="${detail.price}" required>
                    </div>
                    <div class="col-6">
                        <label>Giá Sale ($) - Nhập 0 nếu không Sale:</label>
                        <input type="number" step="0.01" name="salePrice" class="form-control" value="${detail.salePrice}">
                    </div>
                </div>

                <div class="row mb-3">
                    <div class="col-6">
                        <label>Số lượng kho:</label>
                        <input type="number" name="quantity" class="form-control" value="${detail.quantity}" required>
                    </div>
                    <div class="col-6">
    <label>Phân loại:</label>
    <select name="type" class="form-select" required>
        <option value="FFXIV" ${detail.type == 'FFXIV' ? 'selected' : ''}>FFXIV</option>
        <option value="Charms" ${detail.type == 'Charms' ? 'selected' : ''}>Charms</option>
        <option value="Standees" ${detail.type == 'Standees' ? 'selected' : ''}>Standees</option>
        <option value="Books" ${detail.type == 'Books' ? 'selected' : ''}>Books</option>
        <option value="Dungeon Meshi" ${detail.type == 'Dungeon Meshi' ? 'selected' : ''}>Dungeon Meshi</option>
        <option value="Wuthering Waves" ${detail.type == 'Wuthering Waves' ? 'selected' : ''}>Wuthering Waves</option>
        <option value="The Hundred Line" ${detail.type == 'The Hundred Line' ? 'selected' : ''}>The Hundred Line</option>
        <option value="Others" ${detail.type == 'Others' ? 'selected' : ''}>Others</option>
    </select>
</div>
                </div>

                <div class="mb-3 form-check">
                    <input type="checkbox" name="isSoldOut" class="form-check-input" id="soldOutCheck" value="true" ${detail.soldOut ? 'checked' : ''}>
                    <label class="form-check-label text-danger fw-bold" for="soldOutCheck">Ép buộc Hết hàng (Sold Out)</label>
                </div>

                <div class="mb-3">
                    <label>Hình ảnh hiện tại:</label><br>
                    <img src="${detail.image}" alt="old img" style="width: 100px; border-radius: 8px; margin-bottom: 10px;"><br>
                    <label>Chọn ảnh mới (Bỏ trống nếu muốn giữ ảnh cũ):</label>
                    <input type="file" name="imageFile" class="form-control" accept="image/*">
                </div>

                <div class="mb-4">
                    <label>Mô tả:</label>
                    <textarea name="description" class="form-control" rows="4">${detail.description}</textarea>
                </div>

                <button type="submit" class="btn btn-success w-100">Lưu thay đổi</button>
                <a href="admin" class="btn btn-secondary w-100 mt-2">Hủy bỏ</a>
            </form>
        </div>
    </div>
</body>
</html>