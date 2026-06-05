package me.foksik.utility.internal.protocol

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
internal annotation class GamePacket(val id: String)

internal interface Packet {
    fun execute(vararg args: Any)
}