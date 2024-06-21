plugins {
    java
    `kotlin-dsl`
    `maven-publish`
    alias(libs.plugins.plugin.publish)
}

group = "com.n-apos"

description = "Simple gradle plugin to automate project version management"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(gradleApi())
    testImplementation(kotlin("test"))
}

@Suppress("UnstableApiUsage")
gradlePlugin {
    website = "https://github.com/n-apos/semantic-version.git"
    vcsUrl = "https://github.com/n-apos/semantic-version.git"

    plugins {
        create("version") {
            group = "com.n-apos"
            id = "com.n-apos.version"
            displayName = "Gradle plugin to automated versioning"
            implementationClass = "com.napos.version.VersionPlugin"
            description = project.description
            tags = listOf("version", "semantic", "automation")
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
