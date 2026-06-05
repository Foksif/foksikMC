package me.foksik.utility.extensions

import me.foksik.utility.api.FoksikChat
import net.kyori.adventure.text.Component

/**
 * Расширение для быстрого перевода строки в Component.
 */
fun String.mini(vararg args: Pair<String, String>): Component =
    FoksikChat.parse(this, args.toMap())

/**
 * Расширение для быстрого перевода списка строк в компоненты.
 */
fun List<String>.mini(vararg args: Pair<String, String>): List<Component> =
    FoksikChat.parseList(this, args.toMap())