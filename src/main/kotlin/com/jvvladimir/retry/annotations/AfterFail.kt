package com.jvvladimir.retry.annotations

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class AfterFail

// Анноттацией помечается метод без аргументов