plugins {
    `kotlin-dsl`
    `maven-publish`
    alias(libs.plugins.plugin.publish)
}

group = "com.napos"

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
    website = "https://github.com/n-apos"
    vcsUrl = "https://github.com/n-apos/semantic-version.git"

    plugins {
        create("semantic-version") {
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
