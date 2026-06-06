plugins {
    kotlin("jvm") version "2.0.0" apply false
}

allprojects {
    group = "me.foksik.foksikmc"
    version = "1.0.4"

    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}