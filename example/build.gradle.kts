plugins {
    kotlin("jvm")
    alias(libs.plugins.napos.version)
}

group = "com.napos"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    testImplementation(kotlin("test"))
}

semanticVersion {
    path = "version.properties"
    project.version = resolvedVersion
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}
