from fastapi import FastAPI
from kafka_consumer import start_consumer
import threading

app = FastAPI()

@app.get("/")
def root():
    return {"status": "Transcriber running"}

# Start Kafka consumer in background
threading.Thread(target=start_consumer, daemon=True).start()
