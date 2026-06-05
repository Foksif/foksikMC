package me.foksik.utility.internal.time

internal interface LangPack {
    fun format(count: Long, unit: TimeUnit): String
}

internal enum class TimeUnit { YEAR, WEEK, DAY, HOUR, MINUTE, SECOND }

internal class EnLangPack : LangPack {
    override fun format(count: Long, unit: TimeUnit): String {
        if (count == 0L) return ""
        val name = when (unit) {
            TimeUnit.YEAR -> "year"
            TimeUnit.WEEK -> "week"
            TimeUnit.DAY -> "day"
            TimeUnit.HOUR -> "hour"
            TimeUnit.MINUTE -> "minute"
            TimeUnit.SECOND -> "second"
        }
        return if (count == 1L) " 1 $name" else " $count ${name}s"
    }
}

internal class RuLangPack : LangPack {
    override fun format(count: Long, unit: TimeUnit): String {
        return when (unit) {
            TimeUnit.YEAR -> Pluralizer.pluralize(count, "год", "года", "лет")
            TimeUnit.WEEK -> Pluralizer.pluralize(count, "неделя", "недели", "недель")
            TimeUnit.DAY -> Pluralizer.pluralize(count, "день", "дня", "дней")
            TimeUnit.HOUR -> Pluralizer.pluralize(count, "час", "часа", "часов")
            TimeUnit.MINUTE -> Pluralizer.pluralize(count, "минута", "минуты", "минут")
            TimeUnit.SECOND -> Pluralizer.pluralize(count, "секунда", "секунды", "секунд")
        }
    }
}