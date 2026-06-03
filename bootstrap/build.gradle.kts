plugins {
    kotlin("jvm")
    `maven-publish`
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.6-R0.1-SNAPSHOT")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}