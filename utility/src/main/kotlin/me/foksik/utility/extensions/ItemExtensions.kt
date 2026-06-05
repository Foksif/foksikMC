package me.foksik.utility.extensions

import org.bukkit.Material

/**
 * Позволяет открыть Билдер прямо из материала.
 */
fun Material.builder(amount: Int = 1): FoksikItems.Builder =
    FoksikItems.builder(this, amount)