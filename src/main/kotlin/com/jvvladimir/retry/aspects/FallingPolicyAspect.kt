package com.jvvladimir.retry.aspects

import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component

@Aspect
@Component
class FallingPolicyAspect {

    @Pointcut("@annotation(AfterFail)")
    fun afterFallMethod() {}



}