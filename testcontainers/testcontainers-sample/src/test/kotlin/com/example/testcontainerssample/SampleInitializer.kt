package com.example.testcontainerssample

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.GenericContainer

class SampleInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {

    val log: Logger = LoggerFactory.getLogger(SampleInitializer::class.java)

    override fun initialize(configurableApplicationContext: ConfigurableApplicationContext) {
        val redisContainer: KGenericContainer = KGenericContainer("redis:latest").withExposedPorts(6379)
        redisContainer.start()

        val containerIpAddress = redisContainer.getContainerIpAddress()
        val mappedPort = redisContainer.getMappedPort(6379)

        log.info("spring.redis.host=${containerIpAddress}")
        log.info("spring.redis.port=${mappedPort}")
        val values = TestPropertyValues.of(
                "spring.redis.host=${containerIpAddress}",
                "spring.redis.port=${mappedPort}"
        )
        values.applyTo(configurableApplicationContext)
        configurableApplicationContext.beanFactory.registerSingleton("redisContainer", redisContainer)
    }
}
class KGenericContainer(imageName: String) : GenericContainer<KGenericContainer>(imageName)