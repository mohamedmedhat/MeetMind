package websocket

import "github.com/gorilla/websocket"

var Clients = make(map[string]*websocket.Conn)
