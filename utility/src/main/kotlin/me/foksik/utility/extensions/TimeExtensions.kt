package me.foksik.utility.extensions

import me.foksik.utility.api.FoksikTime
import me.foksik.utility.api.model.DurationValue

/**
 * Позволяет быстро распарсить строку времени: "5m".toDuration()
 */
fun String.toDuration(): DurationValue = FoksikTime.parse(this)

// Сахар
val Long.ticks: DurationValue get() = FoksikTime.fromTicks(this)
val Int.ticks: DurationValue get() = FoksikTime.fromTicks(this.toLong())

val Long.seconds: DurationValue get() = FoksikTime.fromMillis(this * 1000L)
val Int.seconds: DurationValue get() = FoksikTime.fromMillis(this * 1000L)