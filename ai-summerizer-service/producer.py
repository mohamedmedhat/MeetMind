from confluent_kafka import Producer
import os, json

producer = Producer({'bootstrap.servers': os.getenv("KAFKA_BOOTSTRAP")})

def publish_summary(email, summary):
    data = json.dumps({"email": email, "summary": summary})
    producer.produce(os.getenv("KAFKA_PRODUCE_TOPIC"), value=data.encode())
    producer.flush()
