plugins {
    `kotlin-dsl`
    `maven-publish`
    alias(libs.plugins.pluginPublish)
}

group = "com.napos"

description = "Simple gradle plugin to automate project version management"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(gradleApi())
    testImplementation(kotlin("test"))
}

@Suppress("UnstableApiUsage")
gradlePlugin {
    website = "https://github.com/n-apos"
    vcsUrl = "https://github.com/n-apos/semantic-version.git"

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

publishing {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

sourceSets {
    val main by getting {
        kotlin.srcDirs("build/generated/sources/kotlin/main")
    }
}