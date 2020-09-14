package com.jvvladimir.retry.utils

import java.util.concurrent.TimeUnit

object TimeUnitConverter {

    fun convertToMillis(delay: Long, timeUnit: TimeUnit) =
            when (timeUnit) {
                TimeUnit.MILLISECONDS -> delay
                TimeUnit.SECONDS -> delay * 1_000
                TimeUnit.HOURS -> delay * 1_000 * 60 * 60
                TimeUnit.DAYS -> delay * 1_000 * 60 * 60 * 24
                TimeUnit.MINUTES -> delay * 1_000 * 60
                TimeUnit.MICROSECONDS -> (delay / 1_000.0).toLong()
                TimeUnit.NANOSECONDS -> (delay / 1_000_000.0).toLong()
            }
}