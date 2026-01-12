# AUTOMATIC RUBIK’S CUBE SOLVER

# Giới thiệu

Đề tài Automatic Rubik’s Cube Solver mô phỏng quá trình xáo trộn, gợi ý, và tự động giải Rubik 3x3 bằng ngôn ngữ Python, sử dụng thư viện VPython để dựng mô hình 3D và Kociemba’s Algorithm để tìm lời giải tối ưu.
Đây là một ứng dụng trực quan của thuật toán tìm kiếm và tối ưu hóa trong thực tế.

# Mục tiêu

Mô phỏng hình khối Rubik 3D tương tác được.

Cài đặt các thao tác xoay từng mặt của khối Rubik.

Áp dụng thuật toán Kociemba để tìm lời giải ngắn nhất.

Hiển thị quá trình giải tự động và minh họa trực quan từng bước.

# Quy trình triển khai

1. Thu thập yêu cầu

Nghiên cứu cách biểu diễn khối Rubik trong không gian 3D.

Tìm hiểu thuật toán Kociemba để giải Rubik tự động.

2. Tiền xử lý & Phân tích

Xây dựng mô hình dữ liệu biểu diễn 54 ô màu.

Định nghĩa các phép xoay tương ứng với các mặt (F, R, B, L, U, D).

3. Mô hình hóa

Dựng khối Rubik 3D bằng VPython (box, vector, rotate).

Mỗi mặt có 9 ô, được gán màu và tọa độ 3D tương ứng.

4. Thuật toán giải

Nhận diện trạng thái hiện tại của Rubik (màu từng ô → ký hiệu F, R, B, L, U, D).

Sử dụng thư viện kociemba để tìm chuỗi xoay tối ưu.

Tự động thực hiện các bước xoay tương ứng.

5. Đánh giá

Kiểm tra độ chính xác của lời giải.

Đánh giá hiệu quả thời gian và trực quan của mô phỏng.

# Thuật toán Kociemba

Thuật toán Kociemba tìm lời giải Rubik với số bước ít nhất bằng cách chia bài toán thành 2 giai đoạn:

Đưa khối Rubik về nhóm con trung gian (Group 1).

Từ đó, tiếp tục tìm lời giải đến trạng thái hoàn chỉnh.

Giải pháp trung bình chỉ cần khoảng 20 bước, nhanh hơn nhiều so với tìm kiếm vét cạn.

# Cấu trúc chương trình

Automatic_Rubik_Solver

- main.py              # Chạy chương trình chính
- cube.py              # Định nghĩa lớp Rubic_Cube và các thao tác xoay
- solve_rubik_cube.py  # Giải mã trạng thái và gọi thuật toán Kociemba

# Cách chạy chương trình

1. Cài đặt thư viện cần thiết
pip install vpython
pip install numpy
pip install kociemba

2. Chạy chương trình
python main.py

# Hướng dẫn sử dụng

Khi chạy chương trình, giao diện VPython xuất hiện gồm:

Các nút xoay thủ công: F, F’, R, R’, B, B’, L, L’, U, U’, D, D’.

Xáo trộn: tạo trạng thái ngẫu nhiên của khối Rubik.

Gợi ý giải: hiển thị chuỗi lời giải (theo thuật toán Kociemba).

Giải tự động: thực hiện lần lượt các bước xoay cho đến khi Rubik được hoàn thành.

# Kết quả đạt được

Mô phỏng khối Rubik 3D chính xác với khả năng xoay linh hoạt.

Giải Rubik tự động trong thời gian ngắn (vài giây).

Trực quan hóa thuật toán tìm kiếm lời giải tối ưu.

# Kết luận

Dự án Automatic Rubik’s Cube Solver thể hiện sự kết hợp giữa lý thuyết thuật toán (Kociemba’s algorithm) và mô phỏng trực quan (VPython).
Ứng dụng giúp sinh viên hiểu rõ cách thuật toán tìm kiếm trạng thái hoạt động, đồng thời tạo hứng thú khi học các môn như Thuật toán ứng dụng và Trí tuệ nhân tạo.

# Thành viên thực hiện

Trương Văn Diệu – Đại học Phenikaa

Môn học: Thuật toán ứng dụng

Giảng viên hướng dẫn: ThS. Trần Đình Tân
