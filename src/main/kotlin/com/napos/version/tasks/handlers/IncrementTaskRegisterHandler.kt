package com.napos.version.tasks.handlers

import com.napos.version.configuration.VersionPluginExtension
import com.napos.version.tasks.IncrementVersionTask
import org.gradle.kotlin.dsl.getByType
import java.io.File

class IncrementTaskRegisterHandler(
    override val task: IncrementVersionTask,
) : TaskRegisterHandler<IncrementVersionTask> {

    override fun handle() {
        with(task) {
            val path = project.extensions.getByType<VersionPluginExtension>()
                .path

            outputFile.convention(
                project.layout.file(path.map { File(it) })
            )
        }
    }
}