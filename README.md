# ⚙️ foksikMC

A modular plugin ecosystem for Bukkit / Paper development.

foksikMC is designed to provide reusable building blocks for Minecraft plugins:
- modular architecture
- lifecycle management
- shared runtime context
- extensible feature modules

---

# 📦 Modules

The ecosystem is split into independent modules:

- core-bootstrap → plugin lifecycle + module system
- utility → shared helpers
- (future) commands → command framework
- (future) config → configuration system
- (future) database → persistence layer

Each module can be used independently.

---

# 📦 Installation

## Add dependency

```kotlin
dependencies {
    // com.github.Foksif.foksikMC:<module_name>:<version>
    implementation("com.github.Foksif.foksikMC:bootstrap:1.0.1")
}
```

## Repositories
```kotlin
repositories {
    mavenCentral()

    maven("https://jitpack.io")
}
```
---

# ⚙️ Usage

```kotlin
class MyPlugin : Bootstrap() {

    init {

        modules(
            DatabaseModule(),
            CommandModule(),
            UserModule()
        )

        preStart {
            logger.info("Plugin is starting...")
        }

        postStart {
            logger.info("Plugin started successfully")
        }
    }
}
```

---

# 🧩 Module example

```kotlin
@Module(
    priority = LoadPriority.HIGH,
    dependsOn = [DatabaseModule::class]
)
class UserModule : IInitializer {

    override fun setup(plugin: JavaPlugin, ctx: PluginContext) {

        val db = ctx.get<DatabaseService>()
            ?: error("Database not found")

        ctx.set(UserService(db))
    }
}
```

---

# 🔗 Dependencies

Modules are sorted by:
- dependsOn (primary)
- priority (secondary)

---

# 🧠 PluginContext

```kotlin
ctx.set(DatabaseService())
val db = ctx.get<DatabaseService>()
```

---

# 🔄 Lifecycle

onEnable:
- preStart
- start()
- resolve modules
- setup modules
- postStart

onDisable:
- shutdown()
- clear context
