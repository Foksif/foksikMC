package me.foksik.utility.internal.builder

import me.foksik.utility.api.FoksikChat
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

internal class ItemBuilderImpl(private val item: ItemStack) {

    constructor(material: Material, amount: Int) : this(ItemStack(material, amount))

    fun meta(block: ItemMeta.() -> Unit): ItemBuilderImpl {
        val meta = item.itemMeta ?: return this
        meta.block()
        item.itemMeta = meta
        return this
    }

    fun name(name: String, args: Map<String, String>): ItemBuilderImpl = meta {
        displayName(FoksikChat.parse(name, args))
    }

    fun lore(lines: List<String>, args: Map<String, String>): ItemBuilderImpl = meta {
        lore(FoksikChat.parseList(lines, args))
    }

    fun modelData(data: Int?): ItemBuilderImpl = meta {
        setCustomModelData(data)
    }

    fun build(): ItemStack = item
}