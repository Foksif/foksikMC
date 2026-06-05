plugins {
    kotlin("jvm")
    `maven-publish`
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "me.foksik.foksikmc"
            artifactId = "utility"
            version = rootProject.version.toString()

            from(components["java"])
        }
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.6-R0.1-SNAPSHOT")
    testImplementation(kotlin("test"))
    compileOnly("net.dmulloy2:ProtocolLib:5.4.0")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}