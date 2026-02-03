package com.napos.version.tasks

import com.napos.version.data.models.Version
import com.napos.version.util.exceptions.PropertiesFileExistsException
import com.napos.version.util.extensions.writeVersion
import org.gradle.api.tasks.TaskAction

abstract class InitializeVersionTask : VersionTask() {

    override val initializeInput: Boolean = false

    @TaskAction
    fun initialize() {
        val file = outputFile.asFile.get()
        if (file.exists()) {
            println("Version file already exists")
        }

        file.writeVersion(Version(), "Initializing version properties file at location : ${file.path}")
    }
}