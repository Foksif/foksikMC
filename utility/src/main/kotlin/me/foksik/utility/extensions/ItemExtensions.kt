package me.foksik.utility.extensions

import me.foksik.utility.api.FoksikItems
import org.bukkit.Material

/**
 * Позволяет открыть Билдер прямо из материала.
 */
fun Material.builder(amount: Int = 1): FoksikItems.Builder =
    FoksikItems.builder(this, amount)