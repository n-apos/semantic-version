package com.napos.version.tasks

import com.napos.version.data.mappers.VersionPropertiesMapper
import com.napos.version.util.exceptions.PropertiesFileNotFoundException
import org.gradle.api.tasks.TaskAction
import java.util.*

abstract class UpgradeVersionTask : VersionTask() {

    private val versionPropertiesMapper: VersionPropertiesMapper = VersionPropertiesMapper()

    @TaskAction
    fun upgrade() {
        val input = inputFile.asFile.get()
        val reader = input.inputStream()

        val output = outputFile.asFile.get()

        if (!input.exists() || !output.exists()) {
            throw PropertiesFileNotFoundException(input.path)
        }

        val props = Properties()
        props.load(reader)
        reader.close()

        props.forEach {
            println("${it.key} = ${it.value}")
        }

        val version = versionPropertiesMapper
            .to(props)
            .apply { upgrade() }


        val writer = input.outputStream()
        versionPropertiesMapper.from(version)
            .store(writer, " Upgraded")
        writer.close()
    }
}