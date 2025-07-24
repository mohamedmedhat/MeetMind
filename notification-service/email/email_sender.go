package email

import (
	"fmt"
	"net/smtp"
	"os"
)

func SendEmail(toEmail, subject, body string) error {
	from := os.Getenv("EMAIL_FROM")
	password := os.Getenv("EMAIL_PASSWORD")

	smtpHost := "smtp.gmail.com"
	smtpPort := "587"

	msg := "From: " + from + "\n" +
		"To: " + toEmail + "\n" +
		"Subject: " + subject + "\n\n" +
		body

	auth := smtp.PlainAuth("", from, password, smtpHost)

	err := smtp.SendMail(smtpHost+":"+smtpPort, auth, from, []string{toEmail}, []byte(msg))
	if err != nil {
		return fmt.Errorf("failed to send email: %v", err)
	}
	return nil
}
