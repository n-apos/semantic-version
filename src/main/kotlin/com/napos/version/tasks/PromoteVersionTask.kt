package com.napos.version.tasks

import com.napos.version.data.mappers.VersionPropertiesMapper
import com.napos.version.data.models.Increment
import com.napos.version.util.exceptions.PropertiesFileNotFoundException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option
import java.util.*

abstract class PromoteVersionTask : VersionTask() {

    private val versionPropertiesMapper: VersionPropertiesMapper = VersionPropertiesMapper()

    @get:Input
    @get:Optional
    @get:Option(description = "Type of increment after promoting")
    var type: Increment = Increment.PATCH


    @TaskAction
    fun upgrade() {
        val input = inputFile.asFile.get()
        val reader = input.inputStream()
        val output = outputFile.asFile.get()

        if (!input.exists()) {
            throw PropertiesFileNotFoundException(input.path)
        }

        val props = Properties()
        props.load(reader)

        val version = versionPropertiesMapper
            .to(props)
            .apply {
                promote(type)
            }

        val writer = output.outputStream()

        versionPropertiesMapper
            .from(version)
            .store(writer, " Promoted")
    }
}