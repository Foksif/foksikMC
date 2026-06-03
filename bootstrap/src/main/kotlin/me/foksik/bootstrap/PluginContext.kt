package me.foksik.bootstrap

import org.bukkit.plugin.java.JavaPlugin

/**
 * Shared plugin runtime context.
 *
 * Acts as lightweight service container.
 *
 * Used for:
 * - sharing services between modules
 * - accessing Bukkit API
 * - storing runtime state
 *
 * Example:
 * ```
 * ctx.set(DatabaseService())
 * val db = ctx.get<DatabaseService>()
 * ```
 */
class PluginContext(private val plugin: JavaPlugin) {

    /**
     * Bukkit server instance.
     */
    val server = plugin.server

    /**
     * Plugin logger.
     */
    val logger = plugin.logger

    /**
     * Plugin configuration.
     */
    val config = plugin.config

    /**
     * Internal service storage.
     */
    private val storage = mutableMapOf<Class<*>, Any>()

    /**
     * Stores value by class type.
     */
    fun <T : Any> set(clazz: Class<T>, value: T) {
        storage[clazz] = value
    }

    /**
     * Retrieves value by class type.
     */
    @Suppress("UNCHECKED_CAST")
    fun <T : Any> get(clazz: Class<T>): T? {
        return storage[clazz] as? T
    }

    /**
     * Stores value using reified type.
     */
    inline fun <reified T : Any> set(value: T) {
        set(T::class.java, value)
    }

    /**
     * Retrieves value using reified type.
     */
    inline fun <reified T : Any> get(): T? {
        return get(T::class.java)
    }

    /**
     * Clears all stored services.
     */
    fun clear() {
        storage.clear()
    }
}