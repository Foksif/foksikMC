package me.foksik.utility.internal.chat

import java.util.regex.Pattern

internal object LegacyConverter {
    private val hexPattern = Pattern.compile("(?i)&#([0-9A-F]{6})")

    private val legacyMap = mapOf(
        '0' to "black", '1' to "dark_blue", '2' to "dark_green", '3' to "dark_aqua",
        '4' to "dark_red", '5' to "dark_purple", '6' to "gold", '7' to "gray",
        '8' to "dark_gray", '9' to "blue", 'a' to "green", 'b' to "aqua",
        'c' to "red", 'd' to "light_purple", 'e' to "yellow", 'f' to "white",
        'k' to "obfuscated", 'l' to "bold", 'm' to "strikethrough",
        'n' to "underlined", 'o' to "italic", 'r' to "reset"
    )

    fun convert(text: String): String {
        var result = text.replace('§', '&')

        val matcher = hexPattern.matcher(result)
        val sb = StringBuffer()
        while (matcher.find()) {
            matcher.appendReplacement(sb, "<#${matcher.group(1)}>")
        }
        matcher.appendTail(sb)
        result = sb.toString()

        legacyMap.forEach { (code, tag) ->
            result = result.replace("&$code", "<$tag>")
        }

        return result
    }
}