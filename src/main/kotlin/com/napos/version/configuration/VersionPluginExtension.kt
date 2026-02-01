package com.napos.version.configuration

import com.napos.version.util.extensions.readVersion
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.Optional
import java.io.File

abstract class VersionPluginExtension {


    /**
     * @deprecated now use 'location' instead
     * Path to the version properties file.
     *
     * Default value: `"${project.projectDir}/version.properties"`
     */
    @Deprecated(
        message = "Migrate to 'location' property instead",
        replaceWith = ReplaceWith("location"),
        level = DeprecationLevel.ERROR,
    )
    @get:Optional
    abstract val path: Property<String>

    /**
     * Path to the version properties file.
     *
     * Default value: `"${project.projectDir}/version.properties"`
     */
    @get:Optional
    abstract val location: Property<String>


    /**
     * Path to the version properties file.
     *
     * Default value: `"${project.projectDir}/version.properties"`
     */
    val resolvedVersion: String
        get() =
            try {
                File(location.get())
                    .readVersion()
                    .toString()
            } catch (e: Exception) {
                e.printStackTrace()
                "0.0.0"
            }
}