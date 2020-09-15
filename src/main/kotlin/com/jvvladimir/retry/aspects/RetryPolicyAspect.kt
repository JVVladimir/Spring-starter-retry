package com.jvvladimir.retry.aspects

import com.jvvladimir.retry.annotations.AfterFail
import com.jvvladimir.retry.annotations.Retry
import com.jvvladimir.retry.utils.TimeUnitConverter
import com.jvvladimir.retry.utils.createInstance
import mu.KLogger
import mu.toKLogger
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.LoggerFactory
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.stereotype.Component
import java.lang.Thread.sleep
import kotlin.reflect.full.createInstance


@Aspect
@Component
class RetryPolicyAspect(
        val context: ConfigurableApplicationContext
) {

    private val log: KLogger = LoggerFactory.getLogger(RetryPolicyAspect::class.java).toKLogger()

    @Around("@annotation(com.jvvladimir.retry.annotations.Retry)")
    fun aroundCallAt(call: ProceedingJoinPoint) {
        log.debug { "Start execution method" }
        val method = (call.signature as MethodSignature).method
        val retry = method.getAnnotation(Retry::class.java)

        val exceptionClasses = retry.suppressedException
        val lastIteration = retry.countOfAttempts - 1

        repeat(retry.countOfAttempts) { count ->
            try {
                call.proceed()
            } catch (suppressedEx: Throwable) {
                // Проверяем, что исключение входит в группу допустимых из аннотации, иначе бросаем непредвиденное исключение
                if (exceptionClasses.isNotEmpty() && !exceptionClasses.any { it.java == suppressedEx.javaClass }) {
                    log.debug { "Unexpected exception: $suppressedEx" }
                    throw suppressedEx
                }

                if (count == lastIteration) {
                    // Бросаем исключение пользователя в случае ошибки, после последней попытки
                    if (retry.throwingException.isNotEmpty()) {
                        val throwingException = retry.throwingException[0]
                        log.debug { "Will be thrown exception: $throwingException" }

                        val newException = if (retry.throwingMessage.isNotBlank()) {
                            throwingException.createInstance(retry.throwingMessage)
                        } else {
                            throwingException.createInstance()
                        }
                        throw newException
                    }

                    // Вызываем метод, помеченный аннотацией AfterFail, для обработки исключения
                    log.debug { "Invoke after fail method" }
                    invokeMethodAnnotatedWith(call.signature.declaringType, AfterFail::class.java)
                }
            }

            if (count != lastIteration) {
                val timeToSleep = TimeUnitConverter.convertToMillis(retry.delay, retry.timeUnit)
                log.debug { "Time to sleep: $timeToSleep ms" }
                sleep(timeToSleep)
            }
        }
    }

    private fun invokeMethodAnnotatedWith(type: Class<*>, annotation: Class<out Annotation>) {
        var klass = type
        while (klass != Any::class.java) {
            for (method in klass.declaredMethods) {
                if (method.isAnnotationPresent(annotation)) {
                    if (method.parameterCount == 0) {
                        method.invoke(context.getBean(type))
                    } else {
                        throw IllegalArgumentException("Method marked with @AfterFail should have no args")
                    }
                }
            }
            klass = klass.superclass
        }
    }
}