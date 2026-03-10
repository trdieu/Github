# Dự án: Phân loại Viêm phổi từ ảnh X-quang ngực (Chest X-ray Pneumonia)

- **Mục tiêu:** Xây dựng mô hình Deep Learning (CNN) để phân loại ảnh X-quang ngực vào 2 lớp: **NORMAL** và **PNEUMONIA**.
- **Dataset gốc (Kaggle):** Chest X-Ray Images (Pneumonia) — paultimothymooney  
  https://www.kaggle.com/datasets/paultimothymooney/chest-xray-pneumonia
- **Dataset dùng để huấn luyện (Google Drive - Zip):**  
  https://drive.google.com/drive/u/0/folders/1VGhzCWWLANc5fC_3QlxAQkUqhJrxwoUc
- **Giảng viên hướng dẫn:** TS. Nguyễn Văn Tới
- **Nhóm thực hiện:** Trương Văn Diệu
- **Lớp:** Xử lý ảnh N01

---

## 1) Tổng quan dự án
Trong dự án này, em xây dựng mô hình học sâu nhằm hỗ trợ phát hiện **viêm phổi (Pneumonia)** từ ảnh **X-quang ngực**.  
Bài toán là phân loại ảnh đầu vào thành 1 trong 2 nhãn:
- `NORMAL` : ảnh phổi bình thường
- `PNEUMONIA` : ảnh phổi bị viêm phổi

Dự án gồm các phần:
- Tiền xử lý dữ liệu ảnh X-quang
- Huấn luyện mô hình CNN bằng TensorFlow/Keras
- Đánh giá mô hình (accuracy, loss, confusion matrix, classification report)
- Xây dựng demo dự đoán ảnh mới bằng Streamlit

---

## 2) Cấu trúc thư mục dự án
```text
Chest X-ray/
├── App/                 # Chứa chương trình demo Streamlit (app.py)
├── data/                # Dữ liệu dataset (train/val/test) - KHÔNG push GitHub
├── Demo/                # Video demo
├── model/               # Chứa model đã train (.keras)
├── notebooks/           # Notebook huấn luyện mô hình (.ipynb)
├── Report/              # Báo cáo cuối kỳ
├── sample_images/       # Ảnh mẫu để test demo
├── Slide/               # Slide thuyết trình
├── README.md            # Giới thiệu dự án
└── requirements.txt     # Danh sách thư viện cần cài
```

---

## 3) Dataset

### 3.1 Dataset Kaggle
Bộ dữ liệu gốc từ Kaggle bao gồm ảnh X-quang ngực được chia theo thư mục train/test/val với 2 nhãn:
- NORMAL
- PNEUMONIA

### 3.2 Dataset dùng trong dự án (Google Drive Zip)
Do dataset Kaggle rất lớn và không thể upload lên GitHub, dataset đã được nén và lưu trên Google Drive.

Link dataset zip:  
https://drive.google.com/drive/u/0/folders/1VGhzCWWLANc5fC_3QlxAQkUqhJrxwoUc

### 3.3 Cách tải và giải nén dataset
1) Tải file `.zip` từ Google Drive  
2) Giải nén vào folder `data/` trong dự án  

Sau khi giải nén, cấu trúc dataset nên như sau:
```text
data/
└── chest_xray/
    ├── train/
    │   ├── NORMAL/
    │   └── PNEUMONIA/
    ├── test/
    │   ├── NORMAL/
    │   └── PNEUMONIA/
    └── val/
        ├── NORMAL/
        └── PNEUMONIA/
```

---

## 4) Thông tin mô hình
Notebook huấn luyện sử dụng **TensorFlow/Keras** và kiến trúc CNN cơ bản:

- Input: ảnh RGB kích thước **180×180**
- Tiền xử lý: `Rescaling(1./255)` (đã nằm trong mô hình)
- Kiến trúc CNN:
  - Conv2D(16) → MaxPooling
  - Conv2D(32) → MaxPooling
  - Conv2D(64) → MaxPooling
  - Flatten → Dense(128)
  - Dense(2, softmax)
- Loss: SparseCategoricalCrossentropy (from_logits=False)
- Optimizer: Adam
- Output classes: `['NORMAL', 'PNEUMONIA']`

File model đã train được lưu tại:
```text
model/xray_model.keras
```

---

## 5) Cài đặt môi trường và thư viện

### 5.1 Tạo môi trường (khuyến nghị dùng venv)
Windows (PowerShell):
```powershell
python -m venv .venv
.\.venv\Scripts\Activate.ps1
```

macOS / Linux:
```bash
python3 -m venv .venv
source .venv/bin/activate
```

### 5.2 Cài thư viện
```bash
pip install -r requirements.txt
```

---

## 6) Chạy demo dự đoán bằng Streamlit

File demo:
```text
App/app.py
```

Chạy app:
```bash
run App/app.py
```

Sau khi chạy, mở trình duyệt:
```text
http://127.0.0.1:7860
```

Tại giao diện:
- Upload ảnh X-quang ngực
- App sẽ dự đoán NORMAL hoặc PNEUMONIA
- Hiển thị xác suất theo %

---

## 7) Huấn luyện lại mô hình (Training)
Notebook huấn luyện nằm trong:
```text
notebooks/
```

Quy trình training cơ bản:
1) Load dataset từ `data/chest_xray/`
2) Tiền xử lý ảnh (resize 180×180)
3) Train CNN với TensorFlow/Keras
4) Đánh giá mô hình
5) Lưu model `.keras` vào folder `model/`

---

## 8) Đánh giá và kết quả
Trong notebook/Report có thực hiện đánh giá mô hình:
- Accuracy / Loss
- Confusion Matrix
- Classification Report  


---

## 9) Tài liệu tham khảo
- Kaggle Dataset: Chest X-Ray Images (Pneumonia)  
  https://www.kaggle.com/datasets/paultimothymooney/chest-xray-pneumonia
- Tài liệu môn học Deep Learning – TS. Nguyễn Văn Tới
