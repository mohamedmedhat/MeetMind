package websocket

import (
	"github.com/gin-gonic/gin"
)

func SendWebSocketMessage(userId, message string) {
	if conn, ok := Clients[userId]; ok {
		conn.WriteJSON(gin.H{
			"type": "SUMMARY_READY",
			"data": message,
		})
	}
}
