package me.foksik.bootstrap

import kotlin.reflect.KClass

/**
 * Metadata annotation for plugin modules.
 *
 * Defines:
 * - module priority
 * - dependencies
 *
 * Example:
 * ```
 * @Module(
 *     priority = LoadPriority.HIGH,
 *     dependsOn = [DatabaseModule::class]
 * )
 * class UserModule : IInitializer
 * ```
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Module(

    /**
     * Optional module name.
     */
    val name: String = "",

    /**
     * Loading priority.
     */
    val priority: LoadPriority = LoadPriority.NORMAL,

    /**
     * Module dependencies.
     *
     * These modules will be initialized before this one.
     */
    val dependsOn: Array<KClass<out IInitializer>> = []
)