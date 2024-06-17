package com.napos.version.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputFile

abstract class VersionTask : DefaultTask() {

    /**
     * Version properties file as input.
     *
     * Default value: file resolved with [com.napos.version.configuration.VersionPluginExtension.path]
     */
    @get:InputFile
    @get:Optional
    abstract val inputFile: RegularFileProperty

    /**
     * Version properties file as output.
     *
     * Default value: file resolved with [com.napos.version.configuration.VersionPluginExtension.path]
     */
    @get:OutputFile
    @get:Optional
    abstract val outputFile: RegularFileProperty


    @Internal
    open val initializeInput: Boolean = true

}