package main

import (
	"github.com/gin-gonic/gin"
	"github.com/mohamedmedhat/notification-service/config"
	"github.com/mohamedmedhat/notification-service/consumers"
	"github.com/mohamedmedhat/notification-service/handlers"
)

func main() {
	config.LoadEnv()

	r := gin.Default()
	handlers.SetupWebSocketRoutes(r)

	go consumers.StartKafkaConsumer()

	r.Run(":8085")
}
