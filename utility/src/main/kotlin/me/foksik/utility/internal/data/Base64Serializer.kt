package me.foksik.utility.internal.data

import org.bukkit.inventory.ItemStack
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream
import java.util.Base64

internal object Base64Serializer {

    fun itemToBase64(item: ItemStack): String {
        val bytes = item.serializeAsBytes()
        return Base64.getEncoder().encodeToString(bytes)
    }

    fun itemFromBase64(base64: String): ItemStack {
        val bytes = Base64.getDecoder().decode(base64)
        return ItemStack.deserializeBytes(bytes)
    }

    fun listToBase64(items: List<ItemStack?>): String {
        val outputStream = ByteArrayOutputStream()
        DataOutputStream(outputStream).use { dataOutput ->
            dataOutput.writeInt(items.size)
            for (item in items) {
                if (item == null || item.type.isAir) {
                    dataOutput.writeInt(0)
                } else {
                    val bytes = item.serializeAsBytes()
                    dataOutput.writeInt(bytes.size)
                    dataOutput.write(bytes)
                }
            }
        }
        return Base64.getEncoder().encodeToString(outputStream.toByteArray())
    }

    fun listFromBase64(base64: String): List<ItemStack?> {
        val bytes = Base64.getDecoder().decode(base64)
        val result = mutableListOf<ItemStack?>()

        DataInputStream(ByteArrayInputStream(bytes)).use { dataInput ->
            val size = dataInput.readInt()
            for (i in 0 until size) {
                val length = dataInput.readInt()
                if (length > 0) {
                    val itemBytes = ByteArray(length)
                    dataInput.readFully(itemBytes)
                    result.add(ItemStack.deserializeBytes(itemBytes))
                } else {
                    result.add(null)
                }
            }
        }
        return result
    }
}