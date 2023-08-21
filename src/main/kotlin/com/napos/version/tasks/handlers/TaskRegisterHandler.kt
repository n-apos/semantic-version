package com.napos.version.tasks.handlers

import com.napos.version.configuration.VersionPluginExtension
import com.napos.version.tasks.VersionTask
import org.gradle.kotlin.dsl.getByType
import java.io.File

abstract class TaskRegisterHandler {

    abstract val task: VersionTask

    fun handle() {
        with(task) {
            val path = project.extensions.getByType<VersionPluginExtension>()
                .path

            outputFile.convention(
                project.layout.file(path.map { File(it) })
            )

            inputFile.convention(
                project.layout.file(path.map { File(it) })
            )
        }
    }
}