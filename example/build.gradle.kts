plugins {
    kotlin("jvm")
    alias(libs.plugins.napos.version)
}

group = "com.n-apos"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

semanticVersion {
    @Suppress("UnstableApiUsage")
    location = "version.properties"
    project.version = resolvedVersion
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}
