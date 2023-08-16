package com.napos.version.tasks

import com.napos.version.data.mappers.VersionPropertiesMapper
import com.napos.version.data.models.Increment
import com.napos.version.util.exceptions.PropertiesFileNotFoundException
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.*
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.options.Option
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

abstract class IncrementVersionTask : VersionTask() {


    @get:Input
    @Option(description = "Type of increment")
    var type: Increment? = null

    @get:Input
    @Option(description = "If specified the suffix should be upgraded")
    var upgrade: Boolean = false

    /**
     * Version properties file.
     *
     * Default value: file resolved with [VersionPluginExtension.path]
     */
    @get:OutputFile
    @get:Optional
    abstract val outputFile: RegularFileProperty

    private val versionPropertiesMapper: VersionPropertiesMapper = VersionPropertiesMapper()

    @Throws(IOException::class)
    @TaskAction
    fun increment() {
        val file = outputFile.asFile.get()
        val input = file.inputStream()

        if (!file.exists()) {
            throw PropertiesFileNotFoundException(path)
        }

        val props = Properties()
        props.load(input)
        input.close()

        val version = versionPropertiesMapper.to(props)

        val operation = if(upgrade) {
            version.upgrade()
            "upgrade"
        } else {
            version.update(type ?: Increment.MINOR)
            "update"
        }
        val output = file.outputStream()

        val timestamp = Date()
        val date = SimpleDateFormat(
            "yyyy-MM-dd",
            Locale.getDefault()
        )
            .format(timestamp)
        val time = SimpleDateFormat(
            "HH:mm",
            Locale.getDefault()
        )
            .format(timestamp)

        versionPropertiesMapper.from(version)
            .store(output, "Modified with operation '$operation' the $date at $time")

    }
}