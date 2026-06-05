package me.foksik.utility.api

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import me.foksik.utility.internal.builder.ItemBuilderImpl

object FoksikItems {

    /**
     * Создает билдер для ItemStack на основе переданного материала.
     */
    fun builder(material: Material, amount: Int = 1): Builder = Builder(material, amount)

    /**
     * Создает билдер на основе уже существующего ItemStack.
     */
    fun builder(itemStack: ItemStack): Builder = Builder(itemStack)

    /**
     * Публичная обёртка над внутренней реализацией.
     */
    class Builder internal constructor(private val impl: ItemBuilderImpl) {
        internal constructor(material: Material, amount: Int) : this(ItemBuilderImpl(material, amount))
        internal constructor(itemStack: ItemStack) : this(ItemBuilderImpl(itemStack))

        fun name(name: String, vararg args: Pair<String, String>): Builder {
            impl.name(name, args.toMap())
            return this
        }

        fun lore(lines: List<String>, vararg args: Pair<String, String>): Builder {
            impl.lore(lines, args.toMap())
            return this
        }

        fun lore(vararg lines: String): Builder {
            impl.lore(lines.toList(), emptyMap())
            return this
        }

        fun modelData(data: Int?): Builder {
            impl.modelData(data)
            return this
        }

        fun build(): ItemStack = impl.build()
    }
}