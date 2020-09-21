package com.jvvladimir.retry.annotations

/**
 * Аннотацией помечается метод без аргументов, который будет вызван единожды в случае ошибки Retry.
 *
 * */

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class AfterFail