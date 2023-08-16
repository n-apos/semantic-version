package com.napos.version.tasks.handlers

import com.napos.version.configuration.VersionPluginExtension
import com.napos.version.tasks.InitializeVersionTask
import org.gradle.api.Action
import org.gradle.kotlin.dsl.getByType
import java.io.File

class InitializeTaskRegisterHandler(
    override val task: InitializeVersionTask,
) : TaskRegisterHandler<InitializeVersionTask> {

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