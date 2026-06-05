package me.foksik.utility.api

import me.foksik.utility.internal.protocol.PacketRegistry
import org.bukkit.Location
import org.bukkit.entity.Player

object FoksikPackets {

    /**
     * Визуально открывает или закрывает сундук для конкретного игрока.
     */
    fun sendChestOpen(player: Player, location: Location, open: Boolean) {
        PacketRegistry.getPacket("chest_open")?.execute(player, location, open)
    }

    /**
     * Показывает игроку [player], как другой игрок [targetPlayer] взмахивает рукой.
     */
    fun sendArmSwing(player: Player, targetPlayer: Player) {
        PacketRegistry.getPacket("arm_swing")?.execute(player, targetPlayer)
    }

    /**
     * Показывает игроку трещины разрушения на блоке.
     * @param stage Стадия от 0 до 9. Любое другое число убирает трещины.
     */
    fun sendBlockCracks(player: Player, location: Location, id: Int, stage: Int) {
        PacketRegistry.getPacket("block_cracks")?.execute(player, location, id, stage)
    }

    /**
     * Визуально открывает или закрывает сундук для ВСЕХ игроков в мире.
     */
    fun sendChestOpenToAll(location: Location, open: Boolean) {
        PacketRegistry.getPacket("chest_open_all")?.execute(location, open)
    }
}