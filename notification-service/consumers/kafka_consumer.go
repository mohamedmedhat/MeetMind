package consumers

import (
	"context"
	"encoding/json"
	"log"

	"github.com/mohamedmedhat/notification-service/email"
	"github.com/mohamedmedhat/notification-service/internal/websocket"
	"github.com/mohamedmedhat/notification-service/models"
	"github.com/segmentio/kafka-go"
)

func StartKafkaConsumer() {
	r := kafka.NewReader(kafka.ReaderConfig{
		Brokers: []string{"kafka:9092"},
		Topic:   "notification.summary.complete",
		GroupID: "notification-service",
	})

	for {
		m, err := r.ReadMessage(context.Background())
		if err != nil {
			log.Println("Kafka read error:", err)
			continue
		}

		var event models.NotificationEvent
		if err := json.Unmarshal(m.Value, &event); err != nil {
			log.Println("Unmarshal error:", err)
			continue
		}

		log.Println("📢 Sending message to:", event.UserID)
		websocket.SendWebSocketMessage(event.UserID, event.Summary)

		err = email.SendEmail(event.Email, "Your video summary is ready!", event.Summary)
		if err != nil {
			log.Println("❌ Email error:", err)
		} else {
			log.Println("📧 Email sent to:", event.Email)
		}
	}
}
