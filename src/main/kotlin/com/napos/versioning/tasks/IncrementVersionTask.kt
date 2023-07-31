package com.napos.versioning.tasks

import com.napos.versioning.models.Increment
import com.napos.versioning.models.Suffix
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction

abstract class IncrementVersionTask : DefaultTask() {

    @get:Input
    abstract val type: Property<Increment>


    @get:Optional
    abstract val suffix: Property<Suffix>

    @get:Optional
    abstract val suffixIncrement: Property<Int>

    @TaskAction
    fun increment() {

    }
}