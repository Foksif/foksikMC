package me.foksik.utility.internal.protocol

import org.bukkit.Bukkit
import java.util.logging.Level

internal object ProtocolChecker {
    fun isReady(): Boolean {
        val enabled = Bukkit.getPluginManager().isPluginEnabled("ProtocolLib")
        if (!enabled) {
            Bukkit.getLogger().log(
                Level.WARNING,
                "[foksikMC-Utility] ProtocolLib не найден! Пакетные утилиты будут отключены."
            )
        }
        return enabled
    }
}