package me.foksik.utility.api

import me.foksik.utility.internal.chat.LegacyConverter
import net.kyori.adventure.text.Component

object FoksikChat {

    /**
     * Парсит строку (MiniMessage, & или HEX) в валидный Component без курсива.
     */
    fun parse(text: String, args: Map<String, String> = emptyMap()): Component {
        val replaced = applyPlaceholders(text, args)
        val converted = LegacyConverter.convert(replaced)
        return miniMessage.deserialize(converted).decoration(TextDecoration.ITALIC, false)
    }

    /**
     * Парсит список строк (например, конфиг лора) в список компонентов.
     */
    fun parseList(lines: List<String>, args: Map<String, String> = emptyMap()): List<Component> =
        lines.map { parse(it, args) }

    private fun applyPlaceholders(text: String, args: Map<String, String>): String {
        if (args.isEmpty()) return text
        var result = text
        args.forEach { (k, v) -> result = result.replace(k, v) }
        return result
    }
}