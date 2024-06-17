package com.napos.version.tasks.handlers

import com.napos.version.tasks.*

object TaskRegisterHandlerFactory {

    fun newInstance(task: VersionTask) =
        when (task) {
            is PrintVersionTask -> PrintTaskRegisterHandler(task)
            is IncrementVersionTask -> IncrementTaskRegisterHandler(task)
            is InitializeVersionTask -> InitializeTaskRegisterHandler(task)
            is UpgradeVersionTask -> UpgradeTaskRegisterHandler(task)
            is PromoteVersionTask -> PromoteTaskRegisterHandler(task)
            else -> throw Exception()
        }
}