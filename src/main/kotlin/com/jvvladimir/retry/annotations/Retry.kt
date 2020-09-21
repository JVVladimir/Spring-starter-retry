package com.jvvladimir.retry.annotations

import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass

/**
 * Аннотацией помечается метод, требующий повторного вызова в случае ошибки
 *
 * */

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FUNCTION)
annotation class Retry(
        val countOfAttempts: Int = 1,
        val delay: Long = 1,
        val timeUnit: TimeUnit = TimeUnit.SECONDS,
        val suppressedException: Array<KClass<out Throwable>> = [],
        val throwingException: Array<KClass<out Throwable>> = [],
        val throwingMessage: String = ""
)
