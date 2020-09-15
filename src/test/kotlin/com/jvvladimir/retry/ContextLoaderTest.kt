package com.jvvladimir.retry

import com.jvvladimir.retry.configuration.RetryAutoConfiguration
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [RetryAutoConfiguration::class])
class ContextLoaderTest {

    @Test
    fun contextLoads() {
    }

}
