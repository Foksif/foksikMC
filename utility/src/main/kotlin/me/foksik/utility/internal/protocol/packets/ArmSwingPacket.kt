package me.foksik.utility.internal.protocol.packets

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolLibrary
import me.foksik.utility.internal.protocol.GamePacket
import me.foksik.utility.internal.protocol.Packet

import org.bukkit.entity.Player

@GamePacket(id = "arm_swing")
internal class ArmSwingPacket : Packet {
    private val manager = ProtocolLibrary.getProtocolManager()

    override fun execute(vararg args: Any) {
        val player = args[0] as Player
        val targetPlayer = args[1] as Player

        val packet = manager.createPacket(PacketType.Play.Server.ANIMATION)
        packet.integers.write(0, targetPlayer.entityId)
        packet.integers.write(1, 0)

        manager.sendServerPacket(player, packet)
    }
}