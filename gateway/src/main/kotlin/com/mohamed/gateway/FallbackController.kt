package com.mohamed.gateway

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono


@RestController
class FallbackController {

    @GetMapping("/fallback/auth")
    fun authFallback(): Mono<ResponseEntity<String>> =
        Mono.just(
            ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Auth Service is currently unavailable. Please try again later.")
        )

    @GetMapping("/fallback/upload")
    fun uploadFallback(): Mono<ResponseEntity<String>> =
        Mono.just(
            ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Upload Service is currently unavailable. Please try again later.")
        )

    @GetMapping("/fallback/transcription")
    fun transcriptionFallback(): Mono<ResponseEntity<String>> =
        Mono.just(
            ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("AI Transcription Service is currently unavailable. Please try again later.")
        )

    @GetMapping("/fallback/summarize")
    fun summarizeFallback(): Mono<ResponseEntity<String>> =
        Mono.just(
            ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Summarize Service is currently unavailable. Please try again later.")
        )

    @GetMapping("/fallback/notification")
    fun notificationFallback(): Mono<ResponseEntity<String>> =
        Mono.just(
            ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Notification Service is currently unavailable. Please try again later.")
        )
}