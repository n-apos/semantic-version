package com.napos.version.tasks.handlers

import com.napos.version.tasks.PromoteVersionTask

class PromoteTaskRegisterHandler(
    override val task: PromoteVersionTask,
) : TaskRegisterHandler()