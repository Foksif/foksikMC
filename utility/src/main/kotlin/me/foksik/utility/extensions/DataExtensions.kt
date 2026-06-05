package me.foksik.utility.extensions

import me.foksik.utility.api.FoksikData
import org.bukkit.Location
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

// Расширения для ItemStack
fun ItemStack.toBase64(): String = FoksikData.serializeItem(this)
fun String.toItemStack(): ItemStack = FoksikData.deserializeItem(this)

// Расширения для инвентарей (содержимого)
fun Inventory.serialize(): String = FoksikData.serializeItems(this.contents.toList())
fun Inventory.deserialize(base64: String) {
    val items = FoksikData.deserializeItems(base64)
    for (i in 0 until minOf(this.size, items.size)) {
        this.setItem(i, items[i])
    }
}

// Расширения для локаций
fun Location.toDataString(): String = FoksikData.serializeLocation(this)
fun String.toLocation(): Location? = FoksikData.deserializeLocation(this)