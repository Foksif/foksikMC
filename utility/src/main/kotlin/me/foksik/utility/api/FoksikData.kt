package me.foksik.utility.api

import me.foksik.utility.internal.data.Base64Serializer
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.inventory.ItemStack

object FoksikData {

    /**
     * Сериализует ItemStack в компактную Base64-строку (сохраняет NBT, кастомные имена 1.20.6+).
     */
    fun serializeItem(item: ItemStack): String = Base64Serializer.itemToBase64(item)

    /**
     * Восстанавливает ItemStack из Base64-строки.
     */
    fun deserializeItem(base64: String): ItemStack = Base64Serializer.itemFromBase64(base64)

    /**
     * Сериализует список предметов (или содержимое инвентаря) в одну строку.
     */
    fun serializeItems(items: List<ItemStack?>): String = Base64Serializer.listToBase64(items)

    /**
     * Восстанавливает список предметов из одной строки.
     */
    fun deserializeItems(base64: String): List<ItemStack?> = Base64Serializer.listFromBase64(base64)

    /**
     * Сериализует Локацию в удобную для базы данных строку: "world;x;y;z;yaw;pitch"
     */
    fun serializeLocation(loc: Location): String {
        return "${loc.world.name};${loc.x};${loc.y};${loc.z};${loc.yaw};${loc.pitch}"
    }

    /**
     * Восстанавливает Локацию из строки. Если мир не найден, вернет null.
     */
    fun deserializeLocation(string: String): Location? {
        val parts = string.split(";")
        if (parts.size < 4) return null
        val world = Bukkit.getWorld(parts[0]) ?: return null
        val x = parts[1].toDouble()
        val y = parts[2].toDouble()
        val z = parts[3].toDouble()
        val yaw = parts.getOrNull(4)?.toFloat() ?: 0f
        val pitch = parts.getOrNull(5)?.toFloat() ?: 0f
        return Location(world, x, y, z, yaw, pitch)
    }
}