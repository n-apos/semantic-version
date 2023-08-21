package com.napos.version.tasks.handlers

import com.napos.version.tasks.IncrementVersionTask

class IncrementTaskRegisterHandler(
    override val task: IncrementVersionTask,
) : TaskRegisterHandler()