package com.napos.version.tasks

import com.napos.version.data.mappers.VersionPropertiesMapper
import com.napos.version.data.models.Version
import com.napos.version.util.exceptions.PropertiesFileNotFoundException
import org.gradle.api.tasks.TaskAction
import java.util.*

abstract class PrintVersionTask : VersionTask() {

    private val versionPropertiesMapper: VersionPropertiesMapper = VersionPropertiesMapper()

    var version: Version = Version()

    @TaskAction
    fun print() {
        val input = inputFile.asFile.get()
        val reader = input.inputStream()

        if (!input.exists()) {
            throw PropertiesFileNotFoundException(input.path)
        }

        val props = Properties()
        props.load(reader)
        reader.close()

        version = versionPropertiesMapper
            .to(props)

        println("Version: $version")
    }
}