package models

type NotificationEvent struct {
	UserID    string `json:"userId"`
	Email     string `json:"email"`
	VideoID   string `json:"videoId"`
	Summary   string `json:"summary"`
	Timestamp string `json:"timestamp"`
}
