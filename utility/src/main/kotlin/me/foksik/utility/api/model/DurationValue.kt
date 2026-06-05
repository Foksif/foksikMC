package me.foksik.utility.api.model

import me.foksik.utility.internal.time.EnLangPack
import me.foksik.utility.internal.time.RuLangPack
import me.foksik.utility.internal.time.TimeUnit
import kotlin.math.min

class DurationValue internal constructor(val toMillis: Long) {

    fun toTicks(): Long = toMillis / 50

    fun toTicksInt(): Int {
        val ticks = toTicks()
        return if (ticks > Int.MAX_VALUE) Int.MAX_VALUE else ticks.toInt()
    }

    fun toSeconds(): Double = toMillis.toDouble() / 1000.0

    /**
     * Превращает время в красивую строку.
     * @param locale язык вывода ("en" или "ru"). По умолчанию "ru".
     */
    fun prettify(
        locale: String = "ru",
        years: Boolean = true,
        weeks: Boolean = true,
        days: Boolean = true,
        hours: Boolean = true,
        minutes: Boolean = true,
        seconds: Boolean = true
    ): String {
        val lang = when (locale.lowercase()) {
            "ru" -> RuLangPack()
            else -> EnLangPack()
        }

        if (toMillis == 0L) {
            return if (locale.lowercase() == "ru") "0 секунд" else "0 seconds"
        }

        var remaining = toMillis
        val builder = StringBuilder()

        val units = listOf(
            UnitConfig(years, 31556952000L, TimeUnit.YEAR),
            UnitConfig(weeks, 604800000L, TimeUnit.WEEK),
            UnitConfig(days, 86400000L, TimeUnit.DAY),
            UnitConfig(hours, 3600000L, TimeUnit.HOUR),
            UnitConfig(minutes, 60000L, TimeUnit.MINUTE),
            UnitConfig(seconds, 1000L, TimeUnit.SECOND)
        )

        for (unit in units) {
            if (!unit.enabled) continue
            val count = remaining / unit.ms
            if (count > 0) {
                builder.append(lang.format(count, unit.type))
                remaining %= unit.ms
            }
        }

        return builder.toString().trim()
    }

    private data class UnitConfig(val enabled: Boolean, val ms: Long, val type: TimeUnit)
}