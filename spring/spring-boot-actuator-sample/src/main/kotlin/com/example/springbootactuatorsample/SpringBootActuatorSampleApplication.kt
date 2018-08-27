package com.example.springbootactuatorsample

import org.slf4j.LoggerFactory
import org.springframework.boot.actuate.audit.AuditEvent
import org.springframework.boot.actuate.audit.AuditEventRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
@EnableScheduling
class SpringBootActuatorSampleApplication(val auditEventRepository: AuditEventRepository) {

    private final val logger = LoggerFactory.getLogger(SpringBootActuatorSampleApplication::class.java)

    @GetMapping
    fun get(): Response {
        auditEventRepository.add(AuditEvent("anonymous", "action", mapOf("method" to "get")))
        return Response("OK!")
    }

    @Scheduled(fixedDelay = 5000)
    fun async() {
        logger.info("Execute async function.")
    }
}

data class Response(val response: String)

fun main(args: Array<String>) {
    runApplication<SpringBootActuatorSampleApplication>(*args)
}
