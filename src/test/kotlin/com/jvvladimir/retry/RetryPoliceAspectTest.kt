package com.jvvladimir.retry

import com.jvvladimir.retry.annotations.Retry
import com.jvvladimir.retry.configuration.RetryAutoConfiguration
import org.junit.jupiter.api.Test
import org.springframework.aop.support.AopUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.stereotype.Component
import org.springframework.test.util.AopTestUtils

@SpringBootTest(classes = [RetryAutoConfiguration::class])
class RetryPoliceAspectTest {

    @Autowired
    lateinit var testService: TestService

    @Test
    fun `invoke @Retry marked method`() {
        val invokedMethod = testService.javaClass.getMethod("start")
        // AopUtils.invokeJoinpointUsingReflection(testService, invokedMethod, emptyArray())
        AopTestUtils.getTargetObject<TestService>(testService).start()
        println(10/ 0.2)
    }

    @Component
    class TestService {

        @Retry(countOfAttempts = 2)
        fun start() {
            println("Test service")
        }
    }
}