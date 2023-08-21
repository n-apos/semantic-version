package com.napos.version.tasks.handlers

import com.napos.version.tasks.InitializeVersionTask

class InitializeTaskRegisterHandler(
    override val task: InitializeVersionTask,
) : TaskRegisterHandler()