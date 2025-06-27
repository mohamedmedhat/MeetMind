from fastapi import FastAPI
from kafka_consumer import start_consumer
import threading

app = FastAPI()

@app.get("/")
def status():
    return {"status": "Summarizer running"}

threading.Thread(target=start_consumer, daemon=True).start()
