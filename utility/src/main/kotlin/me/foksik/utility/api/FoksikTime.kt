package me.foksik.utility.api

import me.foksik.utility.api.model.DurationValue
import me.foksik.utility.internal.time.TimeParserImpl

object FoksikTime {

    /**
     * Создает объект времени из строки (например: "1d 5h", "10.5s", "20t").
     */
    fun parse(input: String): DurationValue {
        val millis = TimeParserImpl.parseToMillis(input)
        return DurationValue(millis)
    }

    /**
     * Создает объект времени напрямую из миллисекунд.
     */
    fun fromMillis(millis: Long): DurationValue = DurationValue(millis)

    /**
     * Создает объект времени напрямую из тиков.
     */
    fun fromTicks(ticks: Long): DurationValue = DurationValue(ticks * 50L)
}