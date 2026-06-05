package me.foksik.utility.extensions

import me.foksik.utility.api.FoksikChat
import net.kyori.adventure.title.Title
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.time.Duration

fun CommandSender.msg(text: String, vararg args: Pair<String, String>) =
    this.sendMessage(FoksikChat.parse(text, args.toMap()))

fun Player.action(text: String, vararg args: Pair<String, String>) =
    this.sendActionBar(FoksikChat.parse(text, args.toMap()))

fun Iterable<CommandSender>.msg(text: String, vararg args: Pair<String, String>) {
    val component = FoksikChat.parse(text, args.toMap())
    this.forEach { it.sendMessage(component) }
}

fun Iterable<Player>.action(text: String, vararg args: Pair<String, String>) {
    val component = FoksikChat.parse(text, args.toMap())
    this.forEach { it.sendActionBar(component) }
}

fun Player.title(
    title: String,
    subtitle: String = "",
    fadeIn: Long = 500,
    stay: Long = 2000,
    fadeOut: Long = 500
) {
    val times = Title.Times.times(
        Duration.ofMillis(fadeIn),
        Duration.ofMillis(stay),
        Duration.ofMillis(fadeOut)
    )
    this.showTitle(Title.title(FoksikChat.parse(title), FoksikChat.parse(subtitle), times))
}