package com.example.springprofilessample

import com.example.springprofilessample.condition.SampleCondition
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Conditional
import org.springframework.context.annotation.Profile
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import javax.sql.DataSource

@SpringBootApplication
class SpringProfilesSampleApplication {

    private val log = LoggerFactory.getLogger(SpringProfilesSampleApplication::class.java)

    @Bean
    @Profile("development")
    fun dataSource(): DataSource {
        return EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .build()
    }

    @Bean
    @Conditional(SampleCondition::class)
    fun beanBySampleCondition(): String {
        log.info("Bean created by SampleCondition.")
        return "beanBySampleCondition"
    }
}

fun main(args: Array<String>) {
    runApplication<SpringProfilesSampleApplication>(*args)
}
