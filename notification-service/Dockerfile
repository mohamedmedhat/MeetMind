# -------- Stage 1: Build --------
FROM golang:1.22-alpine AS builder

RUN apk add --no-cache git tzdata

WORKDIR /app

COPY go.mod go.sum ./
RUN go mod download

COPY . .

RUN go build -o notification-service main.go

# -------- Stage 2: Run --------
FROM alpine:latest

RUN adduser -D appuser

WORKDIR /app

COPY --from=builder /app/notification-service .

USER appuser

EXPOSE 8085

CMD ["./notification-service"]
