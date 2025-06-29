package com.mohamed.gateway

import kotlinx.coroutines.runBlocking
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class FallbackController {

    @GetMapping("/fallback/auth")
    fun authFallback(): ResponseEntity<String> = runBlocking {
        return@runBlocking ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
            .body("Auth Service is currently unavailable. Please try again later.")
    }

    @GetMapping("/fallback/upload")
    fun uploadFallback(): ResponseEntity<String> = runBlocking {
        return@runBlocking ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
            .body("Upload Service is currently unavailable. Please try again later.")
    }

    @GetMapping("/fallback/transcription")
    fun transcriptionFallback(): ResponseEntity<String> = runBlocking {
        return@runBlocking ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
            .body("AI Transcription Service is currently unavailable. Please try again later.")
    }

    @GetMapping("/fallback/summarize")
    fun summarizeFallback(): ResponseEntity<String> = runBlocking {
        return@runBlocking ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
            .body("Summarize Service is currently unavailable. Please try again later.")
    }

    @GetMapping("/fallback/notification")
    fun notificationFallback(): ResponseEntity<String> = runBlocking {
        return@runBlocking ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
            .body("Notification Service is currently unavailable. Please try again later.")
    }
}