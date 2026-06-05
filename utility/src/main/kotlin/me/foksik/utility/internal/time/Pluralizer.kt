package me.foksik.utility.internal.time

import kotlin.math.abs

internal object Pluralizer {
    fun pluralize(count: Long, one: String, two: String, five: String): String {
        if (count == 0L) return ""
        val absCount = abs(count)
        val mod10 = absCount % 10
        val mod100 = absCount % 100

        return when {
            mod100 in 11..14 -> " $count $five"
            mod10 == 1L -> " $count $one"
            mod10 in 2..4 -> " $count $two"
            else -> " $count $five"
        }
    }
}