package com.napos.version.tasks.resolvers

import com.napos.version.tasks.*

object TaskNameResolver {

    fun <T : VersionTask> getName(clazz: Class<T>): String =
        when (clazz) {
            IncrementVersionTask::class.java -> INCREMENT_TASK_NAME
            InitializeVersionTask::class.java -> INITIALIZE_TASK_NAME
            UpgradeVersionTask::class.java -> UPGRADE_TASK_NAME
            PromoteVersionTask::class.java -> PROMOTE_TASK_NAME
            else -> throw Exception()
        }

    private const val INCREMENT_TASK_NAME = "incrementVersion"
    private const val INITIALIZE_TASK_NAME = "initializeVersion"
    private const val UPGRADE_TASK_NAME = "upgradeVersion"
    private const val PROMOTE_TASK_NAME = "promoteVersion"
}