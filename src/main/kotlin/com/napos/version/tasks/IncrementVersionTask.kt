package com.napos.version.tasks

import com.napos.version.data.mappers.VersionPropertiesMapper
import com.napos.version.data.models.Increment
import com.napos.version.util.exceptions.PropertiesFileNotFoundException
import org.gradle.api.tasks.*
import org.gradle.api.tasks.options.Option
import java.io.IOException
import java.util.*

abstract class IncrementVersionTask : VersionTask() {


    @get:Input
    @Option(description = "Type of increment")
    var type: Increment? = null

    private val versionPropertiesMapper: VersionPropertiesMapper = VersionPropertiesMapper()

    @Throws(IOException::class)
    @TaskAction
    fun increment() {
        val input = inputFile.asFile.get()
        val reader = input.inputStream()

        val output = outputFile.asFile.get()

        if (!input.exists() && !output.exists()) {
            throw PropertiesFileNotFoundException(path)
        }

        val props = Properties()
        props.load(reader)
        reader.close()

        val version = versionPropertiesMapper.to(props)

        type?.let {
            version.update(it)
        }

        val writer = output.outputStream()

        versionPropertiesMapper.from(version)
            .store(writer, "Increment of type : $type")
    }
}