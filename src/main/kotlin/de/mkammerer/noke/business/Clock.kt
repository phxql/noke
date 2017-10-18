package de.mkammerer.noke.business

import org.springframework.stereotype.Component
import java.time.ZonedDateTime

interface Clock {
    fun now(): ZonedDateTime
}

@Component
class ClockImpl : Clock {
    override fun now(): ZonedDateTime = ZonedDateTime.now()
}