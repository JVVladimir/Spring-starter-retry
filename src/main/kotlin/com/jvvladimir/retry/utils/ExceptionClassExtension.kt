package com.jvvladimir.retry.utils

import kotlin.reflect.KClass

fun <T : Throwable> KClass<T>.createInstance(message: String): T {
    val stringArgConstructor = constructors.singleOrNull {
        it.parameters.size == 1 && it.parameters[0].type.classifier == String::class
    }
            ?: throw IllegalArgumentException("Class should have a single one-arg constructor: $this")
    return stringArgConstructor.call(message)
}