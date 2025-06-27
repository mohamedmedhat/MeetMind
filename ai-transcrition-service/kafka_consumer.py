from confluent_kafka import Consumer
from transcriber import transcribe_audio
from producer import publish_transcription
import json, os

def start_consumer():
    consumer = Consumer({
        'bootstrap.servers': os.getenv("KAFKA_BOOTSTRAP"),
        'group.id': 'transcriber-group',
        'auto.offset.reset': 'earliest'
    })
    consumer.subscribe([os.getenv("KAFKA_CONSUME_TOPIC")])

    while True:
        msg = consumer.poll(1.0)
        if msg is None or msg.error():
            continue

        data = json.loads(msg.value().decode())
        file_path = os.path.join(os.getenv("AUDIO_FOLDER"), data['fileName'])
        user_email = data['email']

        transcript = transcribe_audio(file_path)
        publish_transcription(user_email, transcript)
