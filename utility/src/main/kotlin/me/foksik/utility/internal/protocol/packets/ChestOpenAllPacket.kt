package me.foksik.utility.internal.protocol.packets

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.wrappers.BlockPosition
import com.comphenix.protocol.wrappers.WrappedBlockData
import me.foksik.utility.internal.protocol.GamePacket
import me.foksik.utility.internal.protocol.Packet
import org.bukkit.Location

@GamePacket(id = "chest_open_all")
internal class ChestOpenAllPacket : Packet {
    private val manager = ProtocolLibrary.getProtocolManager()

    override fun execute(vararg args: Any) {
        val location = args[0] as Location
        val open = args[1] as Boolean

        val packet = manager.createPacket(PacketType.Play.Server.BLOCK_ACTION)
        packet.blockPositionModifier.write(0, BlockPosition(location.blockX, location.blockY, location.blockZ))
        packet.integers.write(0, 1)
        packet.integers.write(1, if (open) 1 else 0)
        packet.blockData.write(0, location.block.blockData as WrappedBlockData?)

        location.world?.players?.forEach { player ->
            manager.sendServerPacket(player, packet)
        }
    }
}