package com.napos.version.tasks

import com.napos.version.util.exceptions.PropertiesFileNotFoundException
import com.napos.version.util.extensions.readVersion
import org.gradle.api.tasks.*
import java.io.IOException
import java.util.*

abstract class PrintVersionTask : VersionTask() {

    @Throws(IOException::class)
    @TaskAction
    fun print() {
        val input = inputFile.asFile.get()

        if (!input.exists()) {
            throw PropertiesFileNotFoundException(path)
        }

        val version = input.readVersion()

        println(version)
    }
}