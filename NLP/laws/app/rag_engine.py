import os
import pickle
import json
import faiss
import torch

from sentence_transformers import SentenceTransformer
from transformers import AutoTokenizer, AutoModelForCausalLM


# Path setup
BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
ARTIFACTS_DIR = os.path.join(BASE_DIR, "artifacts")

CONFIG_PATH = os.path.join(ARTIFACTS_DIR, "config.json")
INDEX_PATH = os.path.join(ARTIFACTS_DIR, "faiss.index")
DOCSTORE_PATH = os.path.join(ARTIFACTS_DIR, "docstore.pkl")


def _assert_exists(path: str, desc: str):
    if not os.path.exists(path):
        raise FileNotFoundError(
            f"Không tìm thấy {desc}: {path}\n"
            f"Hãy kiểm tra cấu trúc thư mục:\n"
            f"{ARTIFACTS_DIR} (phải có config.json, faiss.index, docstore.pkl)"
        )


_assert_exists(CONFIG_PATH, "config.json")
_assert_exists(INDEX_PATH, "faiss.index")
_assert_exists(DOCSTORE_PATH, "docstore.pkl")


# Load artifacts
with open(CONFIG_PATH, "r", encoding="utf-8-sig") as f:
    config = json.load(f)

index = faiss.read_index(INDEX_PATH)

with open(DOCSTORE_PATH, "rb") as f:
    docstore = pickle.load(f)


# Config defaults (đỡ crash)
EMB_MODEL = config.get(
    "embedding_model", "sentence-transformers/paraphrase-multilingual-MiniLM-L12-v2"
)
LLM_MODEL = config.get("llm_model", "Qwen/Qwen2-1.5B-Instruct")
DEFAULT_TOPK = int(config.get("top_k", 5))


# Load models
embedder = SentenceTransformer(EMB_MODEL)

tokenizer = AutoTokenizer.from_pretrained(LLM_MODEL, trust_remote_code=True)


USE_CPU = False

if USE_CPU:
    model = AutoModelForCausalLM.from_pretrained(
        LLM_MODEL, device_map="cpu", torch_dtype=torch.float32, trust_remote_code=True
    )
else:
    model = AutoModelForCausalLM.from_pretrained(
        LLM_MODEL, device_map="auto", torch_dtype=torch.float16, trust_remote_code=True
    )

model.eval()


# RAG function
def rag_answer(query, top_k=None, max_new_tokens=256):
    if top_k is None:
        top_k = DEFAULT_TOPK

    # Retrieval
    q_vec = embedder.encode(
        [query], convert_to_numpy=True, normalize_embeddings=True
    ).astype("float32")

    scores, ids = index.search(q_vec, top_k)

    contexts = []
    citations = []

    for i in ids[0]:
        # docstore thường là list hoặc dict chứa {"text":..., "citation":...}
        item = docstore[i]
        contexts.append(item.get("text", ""))
        citations.append(item.get("citation", "Nguồn không rõ"))

    # Giảm nhiễu: ưu tiên câu chứa "người lao động"
    filtered = [c for c in contexts if "người lao động" in c.lower()]
    if len(filtered) < 2:
        filtered = contexts
    filtered = filtered[:2]
    context_text = "\n".join(f"- {c}" for c in filtered if str(c).strip())

    # Prompt
    system_prompt = (
        "Bạn là chatbot tư vấn pháp luật Việt Nam. "
        "Chỉ trả lời dựa trên ngữ cảnh pháp luật được cung cấp. "
        "Không suy đoán hoặc bịa thông tin. "
        "Nếu không đủ dữ liệu, hãy trả lời: "
        "'Chưa đủ dữ liệu pháp luật để kết luận.'"
    )

    user_prompt = f"""
Câu hỏi: {query}

Ngữ cảnh pháp luật:
{context_text}

Hãy trả lời ngắn gọn, đúng trọng tâm và có thể viện dẫn điều luật nếu phù hợp.
"""

    messages = [
        {"role": "system", "content": system_prompt},
        {"role": "user", "content": user_prompt},
    ]

    prompt = tokenizer.apply_chat_template(
        messages, tokenize=False, add_generation_prompt=True
    )

    inputs = tokenizer(prompt, return_tensors="pt").to(model.device)

    # Generation
    with torch.no_grad():
        output = model.generate(
            **inputs,
            max_new_tokens=max_new_tokens,
            temperature=0.2,
            top_p=0.9,
            do_sample=False,
        )

    generated = tokenizer.decode(output[0], skip_special_tokens=True)

    # Chỉ lấy phần assistant
    if "assistant" in generated:
        answer = generated.split("assistant")[-1].strip()
    else:
        answer = generated.strip()

    # unique citations
    citations = list(dict.fromkeys([c for c in citations if str(c).strip()]))

    return answer, citations


# Kiểm tra CPU/GPU
print("MODEL DEVICE:", next(model.parameters()).device)
