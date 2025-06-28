# -------- Stage 1: Build --------
FROM gradle:8.4.0-jdk21 AS builder

COPY --chown=gradle:gradle . /home/gradle/project
WORKDIR /home/gradle/project

RUN gradle clean build -x test

# -------- Stage 2: Run with Distroless --------
FROM gcr.io/distroless/java21-debian12:nonroot

WORKDIR /app

COPY --from=builder /home/gradle/project/build/libs/*.jar app.jar

EXPOSE 8080

CMD ["app.jar"]
