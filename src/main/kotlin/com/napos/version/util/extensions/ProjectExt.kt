package com.napos.version.util.extensions

import com.napos.version.configuration.VersionPluginExtension
import com.napos.version.tasks.PrintVersionTask
import com.napos.version.tasks.resolvers.TaskNameResolver.PRINT_TASK_NAME
import com.napos.version.util.constants.DEFAULT_PROPERTIES_FILE_NAME
import com.napos.version.util.constants.EXTENSION_NAME
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create

fun Project.version(configure: Action<VersionPluginExtension>) {
    val ext = extensions.getByType(VersionPluginExtension::class.java)

    if (ext.location.isPresent) {
        ext.location.set(DEFAULT_PROPERTIES_FILE_NAME)
    }

    configure.execute(ext)
}

val Project.serializedVersion: String
    get() =
        (tasks.getByName(PRINT_TASK_NAME) as PrintVersionTask)
            .apply { print() }
            .version
            .toString()