package me.foksik.bootstrap

import org.bukkit.plugin.java.JavaPlugin

/**
 * Base bootstrap class for Bukkit plugins.
 *
 * Provides:
 * - module system with dependency resolution
 * - lifecycle hooks (preStart / postStart)
 * - shared PluginContext (service container)
 *
 * Execution order:
 * 1. preStart hook
 * 2. start()
 * 3. module resolution
 * 4. module setup()
 * 5. postStart hook
 *
 * Example:
 * ```
 * class MyPlugin : Bootstrap() {
 *
 *     init {
 *         modules(
 *             DatabaseModule(),
 *             CommandModule()
 *         )
 *
 *         preStart {
 *             logger.info("Starting plugin...")
 *         }
 *
 *         postStart {
 *             logger.info("Plugin started!")
 *         }
 *     }
 * }
 * ```
 */
abstract class Bootstrap : JavaPlugin() {

    /**
     * Registered modules for this plugin.
     */
    private val modules = mutableListOf<IInitializer>()

    /**
     * Hook executed before startup.
     */
    private var preStartHook: (PluginContext.() -> Unit)? = null

    /**
     * Hook executed after startup.
     */
    private var postStartHook: (PluginContext.() -> Unit)? = null

    /**
     * Shared runtime context.
     */
    private lateinit var ctx: PluginContext

    // -----------------------------
    // DSL API
    // -----------------------------

    /**
     * Registers plugin modules.
     */
    fun modules(vararg init: IInitializer) {
        modules += init
    }

    /**
     * Pre-start lifecycle hook.
     */
    fun preStart(block: PluginContext.() -> Unit) {
        preStartHook = block
    }

    /**
     * Post-start lifecycle hook.
     */
    fun postStart(block: PluginContext.() -> Unit) {
        postStartHook = block
    }

    // -----------------------------
    // LIFECYCLE
    // -----------------------------

    override fun onEnable() {
        ctx = PluginContext(this)

        // BEFORE anything
        preStartHook?.invoke(ctx)

        // optional override hook
        start(ctx)

        // resolve + run modules
        val ordered = resolveModules(modules)

        ordered.forEach { module ->
            if (!module.enabled(ctx)) return@forEach
            module.setup(this, ctx)
        }

        postStartHook?.invoke(ctx)
    }

    override fun onDisable() {
        shutdown(ctx)
        ctx.clear()
    }

    /**
     * Optional startup hook.
     */
    open fun start(ctx: PluginContext) {}

    /**
     * Optional shutdown hook.
     */
    open fun shutdown(ctx: PluginContext) {}

    /**
     * Resolves modules by dependencies + priority.
     *
     * Throws if:
     * - dependency is missing
     * - cycle detected
     */
    private fun resolveModules(input: List<IInitializer>): List<IInitializer> {
        val result = mutableListOf<IInitializer>()

        val visited = mutableSetOf<Class<*>>()
        val stack = mutableSetOf<Class<*>>()

        val moduleMap: Map<Class<out IInitializer>, IInitializer> =
            input.associateBy {
                it::class.java as Class<out IInitializer>
            }

        val metaMap: Map<Class<*>, Module?> =
            input.associateBy(
                keySelector = { it::class.java },
                valueTransform = {
                    it::class.java.getAnnotation(Module::class.java)
                }
            )

        val sortedInput = input.sortedByDescending {
            metaMap[it::class.java]?.priority?.ordinal ?: 0
        }

        fun visit(module: IInitializer) {
            val clazz = module::class.java
            if (clazz in visited) return
            if (clazz in stack) {
                error("Cycle detected in modules: ${clazz.simpleName}")
            }

            stack += clazz

            val deps = metaMap[clazz]?.dependsOn.orEmpty()
            for (dep in deps) {
                val dependency = moduleMap[dep.java]
                    ?: error(
                        "Missing dependency: ${dep.simpleName} for ${clazz.simpleName}"
                    )

                visit(dependency)
            }

            stack -= clazz
            visited += clazz

            result += module
        }

        sortedInput.forEach(::visit)

        return result
    }
}