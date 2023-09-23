@file:Suppress("UnstableApiUsage")

plugins {
    id("java")
}

val pluginVersion: String by extra
val minecraftVersion: String by extra

group = "net.tonimatasdev-stuff"
version = pluginVersion

base {
    archivesName.set("Stuff")
}

repositories {
    mavenCentral()
    maven(url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:$minecraftVersion-R0.1-SNAPSHOT")
}

tasks.withType<ProcessResources> {
    filesMatching("plugin.yml") {
        expand("version" to version)
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}