package com.napos.version.tasks.handlers

import com.napos.version.tasks.VersionTask

interface TaskRegisterHandler<Task : VersionTask> {

    abstract val task: Task

    fun handle()
}