package com.napos.version.tasks

import com.napos.version.data.models.Increment
import com.napos.version.util.exceptions.PropertiesFileNotFoundException
import com.napos.version.util.extensions.readVersion
import com.napos.version.util.extensions.writeVersion
import org.gradle.api.tasks.*
import org.gradle.api.tasks.options.Option
import java.io.IOException
import java.util.*

abstract class IncrementVersionTask : VersionTask() {


    @get:Input
    @Option(description = "Type of increment")
    var type: Increment? = null

    @Throws(IOException::class)
    @TaskAction
    fun increment() {
        val file = inputFile.asFile.get()

        if (!file.exists()) {
            throw PropertiesFileNotFoundException(path)
        }

        val version = file.readVersion()

        type?.let {
            version.update(it)
        }

        file.writeVersion(version, "Increment of type : $type")
    }
}