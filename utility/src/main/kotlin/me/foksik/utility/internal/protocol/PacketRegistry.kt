package me.foksik.utility.internal.protocol

import me.foksik.utility.internal.protocol.packets.*

internal object PacketRegistry {
    private val registry = mutableMapOf<String, Packet>()

    init {
        if (ProtocolChecker.isReady()) {
            registerPackets()
        }
    }

    fun getPacket(id: String): Packet? = registry[id]

    private fun registerPackets() {
        register(ChestOpenPacket())
        register(ArmSwingPacket())
        register(BlockCracksPacket())
        register(ChestOpenAllPacket())
    }

    private fun register(packet: Packet) {
        val clazz = packet::class.java
        val annotation = clazz.getAnnotation(GamePacket::class.java)
            ?: throw IllegalArgumentException("Класс ${clazz.simpleName} обязан иметь декоратор @GamePacket!")

        registry[annotation.id] = packet
    }
}