package com.napos.version.util.extensions

import com.napos.version.configuration.VersionPluginExtension
import com.napos.version.util.constants.DEFAULT_PROPERTIES_FILE_NAME
import com.napos.version.util.constants.EXTENSION_NAME
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

fun Project.version(configure: Action<VersionPluginExtension>) {
    val ext = extensions.create<VersionPluginExtension>(EXTENSION_NAME)

    if(!ext.path.isPresent) {
        ext.path.set(DEFAULT_PROPERTIES_FILE_NAME)
    }

    configure.execute(ext)
}