
package com.mohamed.gateway

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class FallbackController {

    @GetMapping("/fallback/auth")
    fun authFallback(): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
            .body("Auth Service is currently unavailable. Please try again later.")
    }
}