# Chatbot Luật Việt Nam sử dụng RAG (Retrieval-Augmented Generation)

## 1. Giới thiệu

Trong những năm gần đây, các mô hình ngôn ngữ lớn (Large Language Models – LLMs) cho thấy khả năng sinh văn bản tự nhiên rất mạnh mẽ. Tuy nhiên, các mô hình này thường gặp hạn chế trong việc trả lời chính xác các câu hỏi liên quan đến tri thức chuyên ngành, đặc biệt là lĩnh vực pháp luật, do dữ liệu huấn luyện không được cập nhật đầy đủ hoặc không mang tính chuyên biệt.

Để khắc phục hạn chế trên, dự án này xây dựng **Chatbot Luật Việt Nam** dựa trên kiến trúc **Retrieval-Augmented Generation (RAG)**. Thay vì chỉ dựa vào tri thức nội tại của mô hình sinh văn bản, hệ thống kết hợp thêm cơ chế truy hồi các văn bản pháp luật liên quan từ một kho tri thức bên ngoài, từ đó nâng cao độ chính xác và giảm hiện tượng sinh thông tin sai (*hallucination*).

---

# 2. Dataset

Dữ liệu pháp luật được sử dụng trong dự án được tải từ Kaggle:

**Vietnamese Legal Dataset – quangbut**

https://www.kaggle.com/datasets/quangbut/vietnamese-legal

Tập dữ liệu gồm:

- `sent_truncated_vbpl_legal_only.csv`  
  Corpus các đoạn văn bản pháp luật đã được tách và rút gọn.

- `dvc.json`  
  Tập dữ liệu câu hỏi dùng để kiểm thử hệ thống.

- `dvc_test.json`  
  Tập dữ liệu test.

Các dữ liệu này được sử dụng làm **knowledge base** cho hệ thống RAG.

---

# 3. Mục tiêu dự án

Mục tiêu của dự án:

- Xây dựng chatbot hỗ trợ **hỏi đáp pháp luật Việt Nam**
- Áp dụng kiến trúc **RAG**
- Truy hồi các đoạn luật liên quan bằng **FAISS**
- Sinh câu trả lời bằng **Large Language Model**
- Đánh giá hiệu quả truy hồi và độ trễ hệ thống

---

# 4. Kiến trúc hệ thống

Pipeline của hệ thống gồm các bước:

### 1. Data Processing
- Nạp dữ liệu văn bản luật
- Làm sạch dữ liệu
- Chuẩn hóa metadata và citation

### 2. Chunking
- Chia văn bản thành các đoạn nhỏ (chunks)
- Mỗi đoạn luật là một đơn vị tri thức

### 3. Embedding
- Sử dụng mô hình Transformer để tạo vector embedding

### 4. Indexing
- Xây dựng FAISS index để tìm kiếm nhanh

### 5. Retrieval
- Truy hồi **top-k đoạn luật liên quan nhất**

### 6. Generation
- Sử dụng LLM để sinh câu trả lời dựa trên context

### 7. Chat Interface
- Giao diện chatbot được xây dựng bằng **Gradio**

---

# 5. Cấu trúc thư mục
```bash
laws/
│
├── app/
│ ├── GUI.py
│ └── rag_engine.py
│
├── artifacts/
│ ├── config.json
│ ├── docstore.pkl
│ └── faiss.index
│
├── data/
│ ├── dvc.json
│ ├── dvc_test.json
│ └── sent_truncated_vbpl_legal_only.csv
│
├── notebooks/
│ └── Chatbot_Laws.ipynb
│
├── demo/
├── report/
├── slide/
│
├── requirements.txt
└── README.md
```
---

# 6. Cài đặt môi trường

Khuyến nghị sử dụng **Python 3.10 hoặc 3.11**

### 1. Clone repository

```bash
git clone <repo-url>
cd laws
```

### 2. Tạo environment
```bash
conda create -n lawbot python=3.10
conda activate lawbot
```

### 3. Cài thư viện
```bash
pip install -r requirements.txt
```

### Hoặc cài thủ công:

```bash
pip install transformers
pip install sentence-transformers
pip install faiss-cpu
pip install gradio
pip install accelerate
pip install torch torchvision torchaudio
```

### 7. Chạy chatbot

Sau khi cài đặt xong, chạy:

python app/GUI.py

Terminal sẽ hiển thị:

Running on local URL: http://127.0.0.1:7860

### 8. Ví dụ câu hỏi
```bash
Công ty có được đơn phương chấm dứt hợp đồng lao động không?
Người lao động có quyền nghỉ việc không cần lý do không?
```

### 9. Công nghệ sử dụng

Python

Transformers

Sentence Transformers

FAISS

Gradio

PyTorch