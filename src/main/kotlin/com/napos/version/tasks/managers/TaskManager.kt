package com.napos.version.tasks.managers

import com.napos.version.tasks.VersionTask
import com.napos.version.tasks.handlers.TaskRegisterHandlerFactory
import com.napos.version.tasks.resolvers.TaskNameResolver
import org.gradle.api.Project

class TaskManager private constructor() {

    private val tasks: MutableSet<Class<*>> = mutableSetOf()

    fun add(task: Class<*>) {
        tasks.add(task)
    }

    fun register(project: Project) {
        tasks.forEach { task ->
            project.tasks.register(
                TaskNameResolver.getName(task as Class<VersionTask>),
                task
            ) {
                TaskRegisterHandlerFactory.newInstance(this)
                    .handle()
            }
        }
    }

    companion object {

        @Volatile
        private var instance: TaskManager? = null

        fun getInstance(): TaskManager =
            synchronized(this) {
                instance ?:
                    TaskManager()
                        .also {
                            instance = it
                        }
            }
    }
}