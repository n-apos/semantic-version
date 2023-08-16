package com.napos.version.tasks.handlers

import com.napos.version.tasks.IncrementVersionTask
import com.napos.version.tasks.InitializeVersionTask
import com.napos.version.tasks.VersionTask

object TaskRegisterHandlerFactory {

    fun newInstance(task: VersionTask) =
        when (task) {
            is IncrementVersionTask -> IncrementTaskRegisterHandler(task)
            is InitializeVersionTask -> InitializeTaskRegisterHandler(task)
            else -> throw Exception()
        }
}