from confluent_kafka import Producer
import os, json

producer = Producer({'bootstrap.servers': os.getenv("KAFKA_BOOTSTRAP")})

def publish_transcription(email, text):
    data = json.dumps({"email": email, "transcript": text})
    producer.produce(os.getenv("KAFKA_PRODUCE_TOPIC"), value=data.encode())
    producer.flush()
