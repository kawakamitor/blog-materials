package com.example.springprofilessample

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.IfProfileValue
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class SpringProfilesSampleApplicationTests {

	@Test
	fun defaultTest() {
        println("Test on default profile.")
	}

    @Test
    @IfProfileValue(name="spring.profiles.active",value = "development")
    fun developmentTest() {
        println("Test on development profile.")
    }

}
