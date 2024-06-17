package com.napos.version.configuration

import com.napos.version.util.extensions.readVersion
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.Optional
import java.io.File

abstract class VersionPluginExtension {


    /**
     * Path to the version properties file.
     *
     * Default value: `"${project.projectDir}/version.properties"`
     */
    @get:Optional
    abstract val path: Property<String>


    /**
     * Path to the version properties file.
     *
     * Default value: `"${project.projectDir}/version.properties"`
     */
    val resolvedVersion: String
        get() =
            try {
                File(path.get())
                    .readVersion()
                    .toString()
            } catch (e: Exception) {
                ""
            }
}