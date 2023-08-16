package com.napos.version

import com.napos.version.tasks.IncrementVersionTask
import com.napos.version.tasks.InitializeVersionTask
import com.napos.version.tasks.managers.TaskManager
import org.gradle.api.Plugin
import org.gradle.api.Project

class VersionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        val taskManager = TaskManager
            .getInstance()
            .apply {
                add(InitializeVersionTask::class.java)
                add(IncrementVersionTask::class.java)
            }

        taskManager.register(target)
    }

}