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
            <input type="text" name="name" required>
        </div>
        
        <div style="margin-bottom: 15px;">
            <label>Giá tiền:</label><br>
            <input type="number" step="0.01" name="price" required>
        </div>
        
        <div style="margin-bottom: 15px;">
            <label>Chọn ảnh sản phẩm:</label><br>
            <input type="file" name="imageFile" accept="image/*" required>
        </div>
        
        <button type="submit" style="padding: 10px 20px; background: #92c375; color: white; border: none;">
            Tải lên & Lưu
        </button>
    </form>
</body>
</html>