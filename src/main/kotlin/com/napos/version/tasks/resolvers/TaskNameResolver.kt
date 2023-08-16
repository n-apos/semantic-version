package com.napos.version.tasks.resolvers

import com.napos.version.tasks.IncrementVersionTask
import com.napos.version.tasks.InitializeVersionTask
import com.napos.version.tasks.VersionTask

object TaskNameResolver {

    fun <T : VersionTask> getName(clazz: Class<T>): String =
        when (clazz) {
            IncrementVersionTask::class.java -> INCREMENT_TASK_NAME
            InitializeVersionTask::class.java -> INITIALIZE_TASK_NAME
            else -> throw Exception()
        }

    const val INCREMENT_TASK_NAME = "incrementVersion"
    const val INITIALIZE_TASK_NAME = "initializeVersion"
}