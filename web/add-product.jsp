<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thêm Sản Phẩm Mới</title>
</head>
<body style="padding: 50px; font-family: Arial;">
    <h2>Thêm sản phẩm và Upload ảnh lên Cloudinary</h2>
    
    <form action="addproduct" method="post" enctype="multipart/form-data">
        <div style="margin-bottom: 15px;">
            <label>Tên sản phẩm:</label><br>
            <input type="text" name="name" required style="width: 100%; padding: 8px;">
        </div>
        
        <div style="margin-bottom: 15px;">
            <label>Giá tiền ($):</label><br>
            <input type="number" step="0.01" name="price" required style="width: 100%; padding: 8px;">
        </div>
        <div style="margin-bottom: 15px;">
    <label>Số lượng kho:</label><br>
    <input type="number" name="quantity" required value="100" style="width: 100%; padding: 8px;">
</div>
<div style="margin-bottom: 15px;">
    <label>Phân loại:</label><br>
    <select name="type" class="form-select" required style="width: 100%; padding: 8px;">
        <option value="FFXIV">FFXIV</option>
        <option value="Charms">Charms</option>
        <option value="Standees">Standees</option>
        <option value="Books">Books</option>
        <option value="Dungeon Meshi">Dungeon Meshi</option>
        <option value="Wuthering Waves">Wuthering Waves</option>
        <option value="The Hundred Line">The Hundred Line</option>
        <option value="Others">Others</option>
    </select>
</div>

        <div style="margin-bottom: 15px;">
            <label>Mô tả chi tiết:</label><br>
            <textarea name="description" rows="5" required style="width: 100%; padding: 8px;"></textarea>
        </div>
        
        <div style="margin-bottom: 25px;">
            <label>Chọn ảnh sản phẩm (Cloudinary):</label><br>
            <input type="file" name="imageFile" accept="image/*" required>
        </div>
        
        <button type="submit" style="padding: 12px 25px; background: #92c375; color: white; border: none; font-weight: bold; cursor: pointer;">
            Tải lên & Lưu Sản Phẩm
        </button>
    </form>
</body>
</html>