package me.foksik.bootstrap

/**
 * Utility function to create module list.
 *
 * Example:
 * ```
 * val modules = bootstrap(
 *     DatabaseModule(),
 *     CommandModule()
 * )
 * ```
 */
fun bootstrap(vararg init: IInitializer): List<IInitializer> {
    return init.toList()
}