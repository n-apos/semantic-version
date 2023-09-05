package com.napos.version

import com.napos.version.configuration.VersionPluginExtension
import com.napos.version.tasks.IncrementVersionTask
import com.napos.version.tasks.InitializeVersionTask
import com.napos.version.tasks.PrintVersionTask
import com.napos.version.tasks.managers.TaskManager
import com.napos.version.util.constants.EXTENSION_NAME
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create

class VersionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            extensions.create<VersionPluginExtension>(EXTENSION_NAME)
//                .apply { extensions.add(EXTENSION_NAME, this) }

            val taskManager = TaskManager
                .getInstance()
                .apply {
                    add(InitializeVersionTask::class.java)
                    add(IncrementVersionTask::class.java)
                    add(PrintVersionTask::class.java)
                }

            taskManager.register(this)
        }
    }

}