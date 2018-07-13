package com.example.consumer.api.useraction

import com.example.consumer.ConsumerApplication
import io.restassured.RestAssured
import io.restassured.RestAssured.given
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [ConsumerApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureStubRunner(ids = ["com.example:provider:+:stubs:8081"], stubsMode = StubRunnerProperties.StubsMode.LOCAL)
class ConsumerApplicationTests {

    @LocalServerPort
    val port: Int = 8080

    @Before
    fun setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port
        RestAssured.basePath = "action"
    }

    @Test
    fun postUserAction01() {

        val postJson: String = """
            {
                "id": 1,
                "userAction": "action1"
            }
        """.trimIndent()

        // @formatter:off
        given()
            .log()
            .all()
            .contentType("application/json;charset=UTF-8")
            .body(postJson)
            .post()
        .then()
            .log()
            .all()
            .statusCode(201)

        given()
            .log()
            .all()
            .contentType("application/json;charset=UTF-8")
            .body(postJson)
            .get("/1")
        .then()
            .log()
            .all()
            .statusCode(200)
            .header("Content-Type","application/json;charset=UTF-8")
        // @formatter:on
    }

}
