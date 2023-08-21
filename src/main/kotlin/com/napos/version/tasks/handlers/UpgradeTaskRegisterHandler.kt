package com.napos.version.tasks.handlers

import com.napos.version.tasks.UpgradeVersionTask

class UpgradeTaskRegisterHandler(
    override val task: UpgradeVersionTask,
) : TaskRegisterHandler()