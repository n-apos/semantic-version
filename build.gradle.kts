import com.napos.version.util.extensions.serializedVersion
import com.napos.version.util.extensions.version

plugins {
    `kotlin-dsl`
    `maven-publish`
    alias(libs.plugins.pluginPublish)
    alias(libs.plugins.version)
    alias(libs.plugins.dokka)
}

group = "com.napos"

description = "Simple gradle plugin to automate project version management"
version = serializedVersion

repositories {
    mavenCentral()
}

dependencies {
    implementation(gradleApi())
    testImplementation(kotlin("test"))
}

@Suppress("UnstableApiUsage")
gradlePlugin {
    website = "website"
    vcsUrl = "vcsUrl"

    plugins {
        create("version") {
            group = "com.napos"
            id = "com.napos.version"
            displayName = "Gradle plugin to automated versioning"
            implementationClass = "com.napos.version.VersionPlugin"
            description = project.description
        }
    }
}

//version {
//
//}

publishing {
    repositories {
        mavenLocal()
    }
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}