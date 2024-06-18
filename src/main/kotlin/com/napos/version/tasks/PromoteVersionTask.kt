package com.napos.version.tasks

import com.napos.version.data.models.Increment
import com.napos.version.util.exceptions.PropertiesFileNotFoundException
import com.napos.version.util.extensions.readVersion
import com.napos.version.util.extensions.writeVersion
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option

abstract class PromoteVersionTask : VersionTask() {

    @get:Input
    @get:Optional
    @get:Option(description = "Type of increment after promoting")
    var type: Increment = Increment.PATCH


    @TaskAction
    fun upgrade() {
        val file = inputFile.asFile.get()

        if (!file.exists()) {
            throw PropertiesFileNotFoundException(file.path)
        }

        val version = file
            .readVersion()
            .apply {
                promote()
            }

        file.writeVersion(version, "Promoted")
    }
}