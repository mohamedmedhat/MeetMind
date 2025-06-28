package com.mohamed.discovery_service

import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableEurekaServer
@SpringBootApplication
class DiscoveryServiceApplication

fun main(args: Array<String>) {
	runApplication<DiscoveryServiceApplication>(*args)
}
