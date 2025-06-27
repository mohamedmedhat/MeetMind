from confluent_kafka import Consumer
from summarizer import summarize_text
from producer import publish_summary
import json, os

def start_consumer():
    consumer = Consumer({
        'bootstrap.servers': os.getenv("KAFKA_BOOTSTRAP"),
        'group.id': 'summarizer-group',
        'auto.offset.reset': 'earliest'
    })
    consumer.subscribe([os.getenv("KAFKA_CONSUME_TOPIC")])

    while True:
        msg = consumer.poll(1.0)
        if msg is None or msg.error():
            continue

        data = json.loads(msg.value().decode())
        email = data["email"]
        transcript = data["transcript"]

        summary = summarize_text(transcript)
        publish_summary(email, summary)
