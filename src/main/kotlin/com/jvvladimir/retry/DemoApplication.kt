//package com.jvvladimir.retry
//
//import org.springframework.boot.autoconfigure.SpringBootApplication
//import org.springframework.boot.runApplication
//import javax.annotation.PostConstruct
//
//@SpringBootApplication
//class DemoApplication
//
//
//fun main(args: Array<String>) {
//    runApplication<DemoApplication>(*args)
//}
//
////// Исполнение любого метода с именем 'setData'
////@Pointcut("execution(* setData(..))")
////
////// Исполнение любого метода, определенного в интерфейсе AccountService
////@Pointcut("execution(* jvvladimir.AccountService.*(..))")
////
////// Исполнение любого метода, определенного в пакете
////@Pointcut("within(jvvladimir.girls..*)")
////
////// Исполнение любого метода, принимающего один аргумент - дату (Runtime)
////@Pointcut("args(java.util.Date)")
////
////// Исполнение любого метода, класс которого наследует TransactionService
////@Pointcut("target(jvvladimir.TransactionService)")
////
////// Исполнение любого метода, класс которого отмечен аннотацией MyAnnotation
////@Pointcut("@annotation(MyAnnotation)")
////
