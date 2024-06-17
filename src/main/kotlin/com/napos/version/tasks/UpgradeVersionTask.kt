package com.napos.version.tasks

import com.napos.version.util.exceptions.PropertiesFileNotFoundException
import com.napos.version.util.extensions.readVersion
import com.napos.version.util.extensions.writeVersion
import org.gradle.api.tasks.TaskAction

abstract class UpgradeVersionTask : VersionTask() {

    @TaskAction
    fun upgrade() {
        val file = inputFile.asFile.get()

        if (!file.exists()) {
            throw PropertiesFileNotFoundException(file.path)
        }

        val version = file
            .readVersion()
            .apply { upgrade() }


        file.writeVersion(version, "Upgraded")
    }
}