package me.foksik.utility.internal.protocol.packets

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.wrappers.BlockPosition
import me.foksik.utility.internal.protocol.GamePacket
import me.foksik.utility.internal.protocol.Packet
import org.bukkit.Location
import org.bukkit.entity.Player

@GamePacket(id = "block_cracks")
internal class BlockCracksPacket : Packet {
    private val manager = ProtocolLibrary.getProtocolManager()

    override fun execute(vararg args: Any) {
        val player = args[0] as Player
        val location = args[1] as Location
        val id = args[2] as Int
        val stage = args[3] as Int

        val packet = manager.createPacket(PacketType.Play.Server.BLOCK_BREAK_ANIMATION)
        packet.integers.write(0, id)
        packet.blockPositionModifier.write(0, BlockPosition(location.blockX, location.blockY, location.blockZ))
        packet.integers.write(1, stage)

        manager.sendServerPacket(player, packet)
    }
}