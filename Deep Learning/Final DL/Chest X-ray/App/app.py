import os
import numpy as np
import streamlit as st
import tensorflow as tf
from PIL import Image

# Cấu hình mặc định
DEFAULT_MODEL_PATH = "model/xray_model.keras"
CLASS_NAMES = ["NORMAL", "PNEUMONIA"]
IMG_SIZE = (180, 180)


def load_keras_model(model_path: str) -> tf.keras.Model:
    if not os.path.exists(model_path):
        raise FileNotFoundError(
            f"Không tìm thấy model tại: {model_path}\n"
            f"Gợi ý: kiểm tra lại file '{DEFAULT_MODEL_PATH}' hoặc chọn đúng đường dẫn ở sidebar."
        )
    return tf.keras.models.load_model(model_path)


def preprocess_image(pil_img: Image.Image) -> np.ndarray:
    """
    - Convert RGB
    - Resize 180x180
    - To numpy + add batch dim
    """
    img = pil_img.convert("RGB").resize(IMG_SIZE)
    arr = np.array(img, dtype=np.float32)
    arr = np.expand_dims(arr, axis=0)  # (1, 180, 180, 3)
    return arr


def predict(model: tf.keras.Model, pil_img: Image.Image):
    x = preprocess_image(pil_img)
    probs = model.predict(x, verbose=0)[0]  # shape (2,)
    idx = int(np.argmax(probs))
    label = CLASS_NAMES[idx]
    return label, probs


# UI
st.set_page_config(page_title="Pneumonia Detection - Chest X-ray", layout="centered")
st.title("Pneumonia Detection (Chest X-ray)")
st.write("Upload ảnh X-quang ngực để dự đoán: NORMAL hoặc PNEUMONIA.")

# Sidebar
st.sidebar.header("Cấu hình")
model_path = st.sidebar.text_input("Đường dẫn model (.keras)", value=DEFAULT_MODEL_PATH)


@st.cache_resource
def get_model_cached(path: str):
    return load_keras_model(path)


try:
    model = get_model_cached(model_path)
    st.sidebar.success("Model loaded successfully.")
except Exception as e:
    st.sidebar.error("Cannot load model.")
    st.error(str(e))
    st.stop()

uploaded = st.file_uploader(
    "Chọn ảnh X-ray (jpg/png/jpeg)", type=["jpg", "jpeg", "png", "bmp", "webp"]
)

if uploaded is not None:
    try:
        image = Image.open(uploaded)
        st.image(image, caption="Ảnh đầu vào", use_container_width=True)

        with st.spinner("Đang dự đoán..."):
            label, probs = predict(model, image)

        st.subheader("Kết quả dự đoán")
        st.write(f"Dự đoán: {label}")

        normal_p = float(probs[0]) * 100.0
        pneumonia_p = float(probs[1]) * 100.0

        st.write("Xác suất dự đoán:")
        st.progress(min(max(pneumonia_p / 100.0, 0.0), 1.0))

        col1, col2 = st.columns(2)
        col1.metric("NORMAL (%)", f"{normal_p:.2f}")
        col2.metric("PNEUMONIA (%)", f"{pneumonia_p:.2f}")

        st.caption(
            "Lưu ý: Demo phục vụ mục đích học tập, không thay thế chẩn đoán y khoa."
        )
    except Exception as e:
        st.error(f"Lỗi khi xử lý ảnh: {e}")
else:
    st.info("Hãy upload 1 ảnh X-quang để bắt đầu dự đoán.")
