package handlers

import (
	"net/http"

	"github.com/gin-gonic/gin"
	"github.com/gorilla/websocket"
	ws "github.com/mohamedmedhat/notification-service/internal/websocket"
)

var upgrader = websocket.Upgrader{
	CheckOrigin: func(r *http.Request) bool {
		return true
	},
}

func SetupWebSocketRoutes(router *gin.Engine) {
	router.GET("/ws/:userId", func(c *gin.Context) {
		userId := c.Param("userId")
		conn, err := upgrader.Upgrade(c.Writer, c.Request, nil)
		if err != nil {
			return
		}
		ws.Clients[userId] = conn
	})
}
