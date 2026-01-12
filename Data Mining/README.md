# DATA MINING PROJECT: WEATHER TEMPERATURE PREDICTION

Mục đích của project này là khai phá dữ liệu thời tiết và dự đoán nhiệt độ trung bình (tempC) bằng cách áp dụng các thuật toán học máy khác nhau như Multiple Linear Regression, Decision Tree Regression và Random Forest Regression.

Output là giá trị số (numerical), được dự đoán dựa trên nhiều đặc trưng (features) như: nhiệt độ tối đa, nhiệt độ tối thiểu, độ che phủ mây, độ ẩm, số giờ nắng, lượng mưa, áp suất và tốc độ gió.

# USE OF ALGORITHMS

Trong khai phá dữ liệu, có nhiều phương pháp dự đoán giá trị liên tục bằng Regression. Dataset được chia thành hai phần:

80% dùng để huấn luyện (training set)

20% dùng để kiểm tra (test set)

Ví dụ: để dự đoán nhiệt độ của Kanpur, Ấn Độ, dữ liệu 8 năm đầu tiên được dùng để train, và 2 năm tiếp theo được dùng để test.

Khác với dự báo thời tiết truyền thống dựa trên mô phỏng vật lý và phương trình vi phân, khai phá dữ liệu kết hợp với AI/ML algorithms cho phép tìm ra các mẫu ẩn trong dữ liệu lịch sử để dự đoán nhiệt độ tương lai.

Kết quả cho thấy, các mô hình ensemble (Random Forest, Gradient Boosting, …) có độ chính xác rất cao, minh chứng cho sức mạnh của kỹ thuật data mining trong dự báo thời tiết.

# METHODOLOGY

Dataset: Thu thập từ Kaggle – Historical Weather Data for Indian Cities.(WorldWeatherOnline.com)

Phạm vi dữ liệu: Thành phố Kanpur (2009–2020, dữ liệu theo giờ).

Nguồn gốc: worldweatheronline.com API và package wwo_hist.

Dataset có thể được dùng cho:

Phân tích và trực quan hóa xu hướng biến đổi khí hậu (global warming)

Khai phá dữ liệu để dự đoán thời tiết trong các ngày, tuần, hoặc mùa tiếp theo

Trong project này, nhóm tập trung vào temperature prediction với các thuật toán chính:

Multiple Linear Regression

Decision Tree Regression

Random Forest Regression

Gradient Boosting Regressor
