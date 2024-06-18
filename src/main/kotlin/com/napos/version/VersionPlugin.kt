package com.napos.version

import com.napos.version.configuration.VersionPluginExtension
import com.napos.version.tasks.*
import com.napos.version.tasks.managers.TaskManager
import com.napos.version.util.constants.EXTENSION_NAME
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

class VersionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        val taskManager = TaskManager
            .getInstance()
            .apply {
                add(PrintVersionTask::class.java)
                add(InitializeVersionTask::class.java)
                add(IncrementVersionTask::class.java)
                add(PromoteVersionTask::class.java)
            }

        taskManager.register(target)
        target
            .extensions
            .create<VersionPluginExtension>(EXTENSION_NAME)
    }

}