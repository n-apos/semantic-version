### Gradle Version Plugin

This Gradle plugin allows you to manage and manipulate versioning for your project. It provides tasks to print,
initialize, increment and promote versions by using a `.properties` file.
In some use cases the automation using a CI/CD can be painful to write especially when you want to make it fit
with your versioning strategy.

### Features

1. **Print Version Task**
    - Task: `PrintVersionTask`
    - Description: Prints the current version read from the version properties file.

2. **Initialize Version Task**
    - Task: `InitializeVersionTask`
    - Description: Initializes the version properties file if it doesn't exist.

3. **Increment Version Task**
    - Task: `IncrementVersionTask`
    - Description: Increments the version based on the specified type (major, minor, patch).

4. **Promote Version Task**
    - Task: `PromoteVersionTask`
    - Description: Promotes the version when it is considered as stable by removing the version suffix parts.

### Configuration

To use the plugin, configure it in your `build.gradle.kts`:

```kotlin
plugins {
    id("com.n-apos.version") version "x.y.z"
}

versionPlugin {
    // Configure path to your version properties file (Optional)
    path = "${project.projectDir}/version.properties"
}
```

### Tasks (continued)

- **Print Version**

```shell
  ./gradlew printVersion
```


- **Initialize Version**

```shell
  ./gradlew initializeVersion
```


- **Increment Version**

```shell
  ./gradlew incrementVersion --type=<MINOR|MAJOR|PATCH|SUFFIX|SUFFIX_INCREMENT>
```

- **Promote Version**

When the version of the project contains a suffix, for example`alpha01`, `beta02`, etc, the `promoteVersion` task
allows to remove the suffix in order to promote when it is considered as stable.

```shell
  ./gradlew promoteVersion
```
