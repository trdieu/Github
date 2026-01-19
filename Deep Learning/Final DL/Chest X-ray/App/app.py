import os
import gradio as gr
import numpy as np
from PIL import Image
import tensorflow as tf

# đường dẫn model
APP_DIR = os.path.dirname(os.path.abspath(__file__))
MODEL_PATH = os.path.abspath(os.path.join(APP_DIR, "..", "model", "xray_model.keras"))

IMG_SIZE = (180, 180)
CLASS_NAMES = ["NORMAL", "PNEUMONIA"]

if not os.path.exists(MODEL_PATH):
    raise FileNotFoundError(
        f"Không tìm thấy model tại:\n{MODEL_PATH}\n"
        "Hãy kiểm tra MODEL_PATH và tên file model."
    )

print("Loading model from:", MODEL_PATH)
model = tf.keras.models.load_model(MODEL_PATH)

# model.input_shape
in_shape = model.input_shape
if isinstance(in_shape, list):
    in_shape = in_shape[0]

# fallback nếu không đọc được shape
try:
    IMG_SIZE = (int(in_shape[1]), int(in_shape[2]))
except Exception:
    IMG_SIZE = (180, 180)

HAS_RESCALING = any(isinstance(l, tf.keras.layers.Rescaling) for l in model.layers)

print("Model input_shape:", model.input_shape)
print("Auto IMG_SIZE:", IMG_SIZE)
print("Has Rescaling inside model:", HAS_RESCALING)


# PREPROCESS
def preprocess(img: Image.Image):
    img = img.convert("RGB").resize(IMG_SIZE)

    arr = np.array(img).astype(np.float32)

    if not HAS_RESCALING:
        arr = arr / 255.0

    arr = np.expand_dims(arr, axis=0)  # (1,H,W,C)
    return arr


# INFER
def infer(img: Image.Image, show_debug: bool):
    if img is None:
        return {"NORMAL": 0.0, "PNEUMONIA": 0.0}, "No image."

    x = preprocess(img)
    pred = model.predict(x, verbose=0)

    debug = []
    debug.append(f"model.input_shape = {model.input_shape}")
    debug.append(f"IMG_SIZE used = {IMG_SIZE}")
    debug.append(f"HAS_RESCALING = {HAS_RESCALING}")
    debug.append(f"x dtype = {x.dtype}, range = [{x.min():.4f}, {x.max():.4f}]")
    debug.append(f"RAW pred = {pred}")

    # sigmoid (1 output)
    if pred.shape[-1] == 1:
        p = float(pred[0][0])
        p = max(0.0, min(1.0, p))

        # Mặc định assume: p = PNEUMONIA
        probs = {CLASS_NAMES[0]: 1 - p, CLASS_NAMES[1]: p}
        return probs, ("\n".join(debug) if show_debug else "")

    # softmax (2 outputs)
    p = pred[0].astype(float).tolist()
    if len(p) >= 2:
        s = p[0] + p[1]
        if s > 0:
            p0, p1 = p[0] / s, p[1] / s
        else:
            p0, p1 = 0.5, 0.5

        probs = {CLASS_NAMES[0]: float(p0), CLASS_NAMES[1]: float(p1)}
        return probs, ("\n".join(debug) if show_debug else "")

    # fallback
    probs = {CLASS_NAMES[0]: 0.5, CLASS_NAMES[1]: 0.5}
    return probs, ("\n".join(debug) if show_debug else "")


# UI
CHATGPT_LIKE_CSS = """
:root { color-scheme: light dark; }

.gradio-container { max-width: 1100px !important; }

h1, h2, h3 { letter-spacing: -0.02em; }

.card {
  border-radius: 16px !important;
  border: 1px solid rgba(128,128,128,0.25) !important;
  padding: 14px !important;
  background: rgba(127,127,127,0.06) !important;
}

.primary-btn button {
  height: 44px !important;
  border-radius: 12px !important;
  font-weight: 600 !important;
}

.muted { opacity: 0.85; font-size: 13px; }
"""


def _on_predict(img, dbg):
    probs, debug = infer(img, dbg)
    if img is None:
        status = "Bạn chưa upload ảnh."
    else:
        status = "Done. Xem Prediction ở panel bên phải."
    return probs, debug, status


def _on_clear():
    return (
        None,
        {"NORMAL": 0.0, "PNEUMONIA": 0.0},
        "",
        "Đã reset. Upload ảnh mới để dự đoán.",
    )


with gr.Blocks() as demo:
    gr.Markdown("# Chest X-ray Classifier")
    gr.Markdown(
        f"Upload ảnh X-ray để dự đoán **{CLASS_NAMES[0]} / {CLASS_NAMES[1]}**.\n\n"
        f"<span class='muted'>Model input: `{model.input_shape}` · Resize: `{IMG_SIZE[0]}x{IMG_SIZE[1]}` · Rescaling in model: `{HAS_RESCALING}`</span>"
    )

    with gr.Row():
        with gr.Column(scale=7, elem_classes=["card"]):
            gr.Markdown("### Input")
            inp = gr.Image(type="pil", label="Kéo thả hoặc chọn ảnh X-ray (JPG/PNG)")
            gr.Markdown(
                "<span class='muted'>Tip: thử ảnh từ tập test trước, rồi thử ảnh ngoài dataset để xem model tổng quát hoá.</span>"
            )

            with gr.Row():
                btn = gr.Button("Predict", elem_classes=["primary-btn"])
                clear = gr.Button("Clear")

            show_debug = gr.Checkbox(
                value=False, label="Show debug (raw output, preprocessing info)"
            )

        with gr.Column(scale=5, elem_classes=["card"]):
            gr.Markdown("### Output")
            out = gr.Label(num_top_classes=2, label="Prediction (Top-2)")
            debug_box = gr.Textbox(label="Debug", lines=10)
            status = gr.Textbox(
                label="Status",
                value="Sẵn sàng. Upload ảnh rồi bấm Predict.",
                interactive=False,
            )

    btn.click(
        fn=_on_predict, inputs=[inp, show_debug], outputs=[out, debug_box, status]
    )
    clear.click(fn=_on_clear, inputs=[], outputs=[inp, out, debug_box, status])


if __name__ == "__main__":
    demo.launch(theme=gr.themes.Soft(), css=CHATGPT_LIKE_CSS)
