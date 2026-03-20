<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Sản Phẩm Mới</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        body { background-color: #f8f9fa; padding: 20px; }
        .form-container { max-width: 700px; margin: 0 auto; background: white; padding: 30px; border-radius: 15px; box-shadow: 0 5px 20px rgba(0,0,0,0.05); }
        .btn-upload { background: #92c375; color: white; border: none; transition: 0.3s; }
        .btn-upload:hover { background: #7ba85f; color: white; }
    </style>
</head>
<body>
    <div class="container">
        <div class="text-center mb-4">
            <a href="admin" class="btn btn-link text-decoration-none text-muted mb-2"><i class="fas fa-arrow-left"></i> Quay lại Dashboard</a>
            <h2 class="fw-bold">Thêm Sản Phẩm Mới</h2>
            <p class="text-muted small">Thông tin sẽ được lưu và ảnh tải lên Cloudinary</p>
        </div>

        <div class="form-container">
            <form action="addproduct" method="post" enctype="multipart/form-data">
                <div class="row">
                    <div class="col-md-8 mb-3">
                        <label class="form-label fw-bold">Tên sản phẩm</label>
                        <input type="text" name="name" class="form-control" required placeholder="Ví dụ: Standee Cherino">
                    </div>
                    <div class="col-md-4 mb-3">
                        <label class="form-label fw-bold">Phân loại</label>
                        <select name="type" class="form-select" required>
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
                </div>

                <div class="row">
                    <div class="col-6 mb-3">
                        <label class="form-label fw-bold">Giá tiền ($)</label>
                        <div class="input-group">
                            <span class="input-group-text">$</span>
                            <input type="number" step="0.01" name="price" class="form-control" required placeholder="0.00">
                        </div>
                    </div>
                    <div class="col-6 mb-3">
                        <label class="form-label fw-bold">Số lượng kho</label>
                        <input type="number" name="quantity" class="form-control" required value="100">
                    </div>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-bold">Mô tả chi tiết</label>
                    <textarea name="description" rows="4" class="form-control" required placeholder="Nhập mô tả sản phẩm tại đây..."></textarea>
                </div>

                <div class="mb-4">
                    <label class="form-label fw-bold">Ảnh sản phẩm</label>
                    <input type="file" name="imageFile" class="form-control" accept="image/*" required>
                    <div class="form-text">Hệ thống sẽ tự động upload lên Cloudinary.</div>
                </div>

                <button type="submit" class="btn btn-upload w-100 py-3 fw-bold shadow-sm">
                    <i class="fas fa-cloud-upload-alt me-2"></i> Tải lên & Lưu Sản Phẩm
                </button>
            </form>
        </div>
    </div>
</body>
</html>