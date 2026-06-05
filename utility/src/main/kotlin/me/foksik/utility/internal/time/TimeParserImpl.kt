package me.foksik.utility.internal.time

import kotlin.math.roundToLong

internal object TimeParserImpl {
    private val pairPattern = Regex("""(\d+(?:[.,]\d+)?)\s*([ywdhmst])""")

    private const val MS_PER_TICK = 50L
    private const val MS_PER_SECOND = 1000L
    private const val MS_PER_MINUTE = 60 * 1000L
    private const val MS_PER_HOUR = 60 * 60 * 1000L
    private const val MS_PER_DAY = 24 * 60 * 60 * 1000L
    private const val MS_PER_WEEK = 7 * 24 * 60 * 60 * 1000L
    private const val MS_PER_YEAR = (365.2425 * 24 * 60 * 60 * 1000).toLong()

    fun parseToMillis(input: String): Long {
        val clean = input.lowercase().trim()
        if (clean.isEmpty()) throw IllegalArgumentException("Строка времени не может быть пустой")

        var totalMillis = 0L
        var found = false

        pairPattern.findAll(clean).forEach { match ->
            val value = match.groupValues[1].replace(',', '.').toDouble()
            val unit = match.groupValues[2]

            totalMillis += when (unit) {
                "y" -> (value * MS_PER_YEAR).roundToLong()
                "w" -> (value * MS_PER_WEEK).roundToLong()
                "d" -> (value * MS_PER_DAY).roundToLong()
                "h" -> (value * MS_PER_HOUR).roundToLong()
                "m" -> (value * MS_PER_MINUTE).roundToLong()
                "s" -> (value * MS_PER_SECOND).roundToLong()
                "t" -> (value * MS_PER_TICK).roundToLong()
                else -> 0L
            }
            found = true
        }

        if (!found) throw IllegalArgumentException("Не удалось распарсить формат времени: $input")
        return totalMillis
    }
}