import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    application
    `java-library`
    id("com.github.johnrengelman.shadow") version "5.0.0"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenCentral()
}

val vertxVersion = "3.7.0"

dependencies {
    api("io.vertx:vertx-core:$vertxVersion")
    runtime("io.netty:netty-tcnative-boringssl-static:2.0.17.Final")
}

val mainVerticle = "Server"

application {
    mainClassName = "io.vertx.core.Launcher"
}

tasks.withType<ShadowJar> {
    manifest {
        attributes["Main-Verticle"] =  mainVerticle

    }
    mergeServiceFiles {
        include("META-INF/services/io.vertx.core.spi.VerticleFactory")
    }
}
