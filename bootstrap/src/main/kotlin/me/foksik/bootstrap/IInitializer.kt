package me.foksik.bootstrap

import org.bukkit.plugin.java.JavaPlugin

/**
 * Represents a plugin module.
 *
 * Modules are initialized during bootstrap startup
 * after dependency resolution.
 *
 * Responsibilities:
 * - register services
 * - initialize systems
 * - setup plugin features
 *
 * Example:
 * ```
 * @Module(dependsOn = [DatabaseModule::class])
 * class UserModule : IInitializer {
 *
 *     override fun setup(plugin: JavaPlugin, ctx: PluginContext) {
 *         val db = ctx.get<Database>()!!
 *         ctx.set(UserService(db))
 *     }
 * }
 * ```
 */
interface IInitializer {

    /**
     * Called when module is initialized.
     */
    fun setup(plugin: JavaPlugin, ctx: PluginContext)

    /**
     * Determines whether module should be enabled.
     */
    fun enabled(ctx: PluginContext): Boolean = true
}