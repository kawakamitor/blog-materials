package com.example.testcontainerssample

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
@ContextConfiguration(initializers = [SampleInitializer::class])
class TestcontainersSampleApplicationTests {

    @Autowired
    lateinit var redisContainer: KGenericContainer;

    @Autowired
    lateinit var redisTemplate: StringRedisTemplate;

    @Test
    fun redisContainerTest() {
        assertThat(redisContainer).isNotNull
    }

    @Test
    fun redisTest01() {
        redisTemplate.opsForValue().set("key01", "value01")
        assertThat(redisTemplate.opsForValue().get("key01")).isEqualTo("value01");
    }

    @Test
    fun redisTest02() {
        redisTemplate.opsForValue().set("key02", "value02")
        assertThat(redisTemplate.opsForValue().get("key02")).isEqualTo("value02");
    }
}