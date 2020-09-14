//package com.jvvladimir.retry
//
//import com.jvvladimir.retry.annotations.AfterFail
//import com.jvvladimir.retry.annotations.Retry
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.RestController
//
//@RestController
//class TestController {
//
//    @Autowired
//    private lateinit var testController: TestController
//
//    @GetMapping("/get")
//    fun get(): String {
//        println("Hello get")
//        testController.go("Vova", 2, 4)
//        return "OK"
//    }
//
//    @Retry(countOfAttempts = 1, suppressedException = [NoSuchElementException::class])
//    fun go(s: String, a: Int, b: Int) {
//        println("I am here!")
//        throw NoSuchElementException("No elem")
//    }
//
//    @AfterFail
//    fun rollBack() {
//        println("Rollback done!")
//    }
//}