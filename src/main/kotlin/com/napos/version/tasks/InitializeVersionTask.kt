package com.napos.version.tasks

import com.napos.version.data.mappers.VersionPropertiesMapper
import com.napos.version.data.models.Version
import com.napos.version.util.exceptions.PropertiesFileExistsException
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

abstract class InitializeVersionTask : VersionTask() {

    /**
     * Version properties file.
     *
     * Default value: file resolved with [VersionPluginExtension.path]
     */
    @get:OutputFile
    @get:Optional
    abstract val outputFile: RegularFileProperty


    @TaskAction
    fun initialize() {
        val file = outputFile.asFile.get()
        if(file.exists()) {
            throw PropertiesFileExistsException(file.path)
        }
        val props = VersionPropertiesMapper()
            .from(Version())

        props.store(file.outputStream(), "Initializing version properties file at location : ${file.path}")
    }
}