package com.napos.version.configuration

import org.gradle.api.provider.Property
import org.gradle.api.tasks.Optional

abstract class VersionPluginExtension {


    /**
     * Path to the version properties file.
     *
     * Default value: `"${project.projectDir}/version.properties"`
     */
    @get:Optional
    abstract val path: Property<String>

}