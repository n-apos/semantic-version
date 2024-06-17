package com.napos.version.tasks.handlers

import com.napos.version.tasks.PrintVersionTask

class PrintTaskRegisterHandler(
    override val task: PrintVersionTask,
) : TaskRegisterHandler()