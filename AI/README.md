# ỨNG DỤNG TRÍ TUỆ NHÂN TẠO TRONG DỰ BÁO THỜI TIẾT

Mục tiêu: Áp dụng Machine Learning để dự đoán nhiệt độ trung bình (tempC) dựa trên dữ liệu thời tiết lịch sử, làm quen với các bước cơ bản của AI/ML từ tiền xử lý dữ liệu đến huấn luyện và đánh giá mô hình.

Đầu ra (Output): Giá trị số (numerical), dựa trên các đặc trưng (features) như:

Nhiệt độ tối đa / tối thiểu

Độ che phủ mây

Độ ẩm

Số giờ nắng

Lượng mưa

Áp suất

Tốc độ gió

# Thuật toán sử dụng

Dữ liệu được chia thành:

80% để huấn luyện (training set)

20% để kiểm tra (test set)

Ví dụ: để dự đoán nhiệt độ tại Kanpur, Ấn Độ, 8 năm dữ liệu đầu tiên dùng để huấn luyện, 2 năm tiếp theo dùng để kiểm tra.

Machine Learning cho phép học các mối quan hệ phi tuyến tính trong dữ liệu thời tiết, khác với các phương pháp truyền thống dựa trên mô phỏng vật lý.

Các mô hình ensemble như Random Forest và Gradient Boosting cho kết quả dự đoán rất chính xác, minh chứng hiệu quả của AI/ML trong dự báo nhiệt độ.

# Phương pháp (Methodology)

Nguồn dữ liệu: Kaggle – Historical Weather Data for Indian Cities (WorldWeatherOnline.com)

Phạm vi dữ liệu: Thành phố Kanpur, 2009–2020 (dữ liệu theo giờ)

Nguồn gốc: worldweatheronline.com API và package wwo_hist

Ứng dụng dữ liệu trong môn học:

Thực hành các bước cơ bản của dự án AI/ML: tiền xử lý dữ liệu, chọn đặc trưng, huấn luyện mô hình và đánh giá kết quả

Khám phá mối quan hệ giữa các yếu tố khí tượng và nhiệt độ

Làm quen với thuật toán hồi quy (Regression) trong dự báo nhiệt độ

Thuật toán chính được áp dụng:

Multiple Linear Regression (Hồi quy tuyến tính đa biến)

Decision Tree Regression (Hồi quy cây quyết định)

Random Forest Regression (Hồi quy rừng ngẫu nhiên)

Gradient Boosting Regressor
