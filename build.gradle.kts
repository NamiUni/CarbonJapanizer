import xyz.jpenilla.resourcefactory.bukkit.Permission
import xyz.jpenilla.resourcefactory.paper.PaperPluginYaml.Load

group = "com.github.namiuni"
version = "1.0.0-SNAPSHOT"

plugins {
    id("java")
    id("com.gradleup.shadow") version "9.1.0"
    id("xyz.jpenilla.resource-factory-paper-convention") version "1.3.0"
    id("xyz.jpenilla.run-paper") version "2.3.1"
    id("xyz.jpenilla.gremlin-gradle") version "0.0.9"
    id("net.kyori.indra.licenser.spotless") version "3.1.3"
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
}

dependencies {
    // Paper
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT") {
        exclude("net.md-5")
    }
    compileOnly("io.github.miniplaceholders:miniplaceholders-api:2.3.0") // MiniPlaceholders
    compileOnly("de.hexaoxi:carbonchat-api:3.0.0-beta.33") // Carbon

    // Libraries
    runtimeDownload("org.spongepowered:configurate-hocon:4.2.0") // config
    runtimeDownload("net.kyori:adventure-serializer-configurate4:4.24.0") // config serializer
    runtimeDownload("net.kyori.moonshine:moonshine-standard:2.0.4") // message

    // Misc
    runtimeDownload("com.google.inject:guice:7.0.1-SNAPSHOT") {
        exclude("com.google.guava")
    }
}

val mainPackage = "$group.carbonjapanizer"
paperPluginYaml {
    author = "Namiu/Unitarou"
    website = "https://github.com/NamiUni"
    apiVersion = "1.21"

    main = "$mainPackage.CarbonJapanizerPaper"
    bootstrapper = "$mainPackage.CarbonJapanizerBootstrap"
    loader = "$mainPackage.CarbonJapanizerLoader"

    permissions {
        register("carbonjapanizer.command.reload") {
            description = "Reloads CarbonJapanizer's config."
            default = Permission.Default.OP
        }
    }

    dependencies {
        server("MiniPlaceholders", Load.BEFORE, false)
        server("LuckPerms", Load.BEFORE, true)
    }
}

indraSpotlessLicenser {
    licenseHeaderFile(rootProject.file("LICENSE_HEADER"))
    property("name", rootProject.name)
    property("author", paperPluginYaml.author)
    property("contributors", paperPluginYaml.contributors)
}

configurations {
    compileOnly {
        extendsFrom(configurations.runtimeDownload.get())
    }
}

tasks {
    shadowJar {
        archiveClassifier = null as String?
        gremlin {
            listOf("xyz.jpenilla.gremlin")
                .forEach {
                    relocate(it, "$mainPackage.libs.$it")
                }
        }
    }

    runServer {
//        systemProperty("com.mojang.eula.agree", "true")
        minecraftVersion("1.21.4")
        downloadPlugins {
            url("https://download.luckperms.net/1573/bukkit/loader/LuckPerms-Bukkit-5.4.156.jar")
            modrinth("miniplaceholders", "wck4v0R0") // バージョンに2.3.0を指定すると何故かVelocityのjarがダウンロードされる
            modrinth("miniplaceholders-placeholderapi-expansion", "1.2.0")
            modrinth("carbon", "3.0.0-beta.28")
            hangar("PlaceholderAPI", "2.11.6")
        }
    }

    writeDependencies {
        repos.add("https://repo.papermc.io/repository/maven-public/")
        repos.add("https://repo.maven.apache.org/maven2/")
    }
}
