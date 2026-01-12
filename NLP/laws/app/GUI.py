import gradio as gr
from rag_engine import rag_answer


def chat_fn(question):
    answer, citations = rag_answer(question)
    cite_text = "\n".join(f"- {c}" for c in citations)
    return answer, cite_text


with gr.Blocks(title="Chatbot Luật Việt Nam") as demo:
    gr.Markdown("# Chatbot Luật Việt Nam")

    question = gr.Textbox(
        label="Câu hỏi pháp luật",
        placeholder="Ví dụ: Người lao động có quyền đơn phương chấm dứt hợp đồng lao động không?",
    )

    answer = gr.Textbox(label="Câu trả lời", lines=6)
    citations = gr.Textbox(label="Nguồn trích dẫn", lines=4)

    btn = gr.Button("Hỏi")

    btn.click(fn=chat_fn, inputs=question, outputs=[answer, citations])

demo.launch()
