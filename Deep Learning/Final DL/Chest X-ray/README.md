# Dự án: Phân loại Viêm phổi từ ảnh X-quang ngực (Chest X-ray Pneumonia)

- **Mục tiêu:** Xây dựng mô hình Deep Learning (CNN) để phân loại ảnh X-quang ngực vào 2 lớp: **NORMAL** và **PNEUMONIA**.
- **Dataset (Kaggle):** Chest X-Ray Images (Pneumonia) — paultimothymooney  
  https://www.kaggle.com/datasets/paultimothymooney/chest-xray-pneumonia
- **Giảng viên hướng dẫn:** TS. Lê Minh Huy
- **Nhóm thực hiện:** Trương Văn Diệu
- **Lớp:** Học Sâu N01

---

## 1) Tổng quan dự án
Trong dự án này, chúng em xây dựng mô hình học sâu nhằm hỗ trợ phát hiện **viêm phổi (Pneumonia)** từ ảnh **X-quang ngực**.  
Bài toán là phân loại ảnh đầu vào thành 1 trong 2 nhãn:

- `NORMAL` : ảnh phổi bình thường
- `PNEUMONIA` : ảnh phổi bị viêm phổi

Dự án gồm các phần:
- Tiền xử lý dữ liệu ảnh X-quang
- Huấn luyện mô hình CNN bằng TensorFlow/Keras
- Đánh giá mô hình (accuracy, loss, confusion matrix, classification report)
- Demo chương trình dự đoán ảnh mới trên VS Code

---

## 2) Cấu trúc thư mục dự án
Dưới đây là cấu trúc thư mục dự án (đúng theo project hiện tại):

├── App/ # (Tuỳ chọn) giao diện demo (nếu có)
├── data/ # Dữ liệu dataset (train/val/test)
├── Demo/ # File demo chạy trên VS Code
├── model/ # Chứa model đã train (.keras)
├── notebooks/ # Notebook huấn luyện mô hình (.ipynb)
├── Report/ # Báo cáo cuối kỳ
├── sample_images/ # Ảnh mẫu để test mô hình
├── Slide/ # Slide thuyết trình
├── README.md # Giới thiệu dự án
└── requirements.txt # Danh sách thư viện cần cài

---

## 3) Thông tin mô hình
Notebook huấn luyện sử dụng **TensorFlow/Keras** và kiến trúc CNN cơ bản:

- Input: ảnh RGB kích thước **180×180**
- Tiền xử lý: `Rescaling(1./255)` (đã nằm trong mô hình)
- Kiến trúc CNN:
  - Conv2D(16) → MaxPool
  - Conv2D(32) → MaxPool
  - Conv2D(64) → MaxPool
  - Flatten → Dense(128)
  - Dense(2, softmax)
- Loss: SparseCategoricalCrossentropy (from_logits=False)
- Optimizer: Adam
- Classes: `['NORMAL', 'PNEUMONIA']`
