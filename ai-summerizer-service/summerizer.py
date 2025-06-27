from transformers import pipeline
import os

summarizer = pipeline("summarization", model=os.getenv("MODEL_NAME"))

def summarize_text(text: str) -> str:
    if len(text) > 3000:
        text = text[:3000]  # truncate if too long
    result = summarizer(text, max_length=150, min_length=30, do_sample=False)
    return result[0]["summary_text"]
