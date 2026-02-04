package com.napos.version.tasks.handlers

import com.napos.version.configuration.VersionPluginExtension
import com.napos.version.tasks.IncrementVersionTask
import com.napos.version.tasks.InitializeVersionTask
import com.napos.version.tasks.PrintVersionTask
import com.napos.version.tasks.PromoteVersionTask
import com.napos.version.util.constants.EXTENSION_NAME
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

class HandlersTest {

    private val project = ProjectBuilder.builder().build()
    private lateinit var extension: VersionPluginExtension

    @BeforeEach
    fun setup() {
        extension = project.extensions.create(EXTENSION_NAME, VersionPluginExtension::class.java)
        extension.location.set("version.properties")
    }

    @Test
    fun `PrintTaskRegisterHandler should configure input and output files`() {
        val task = project.tasks.create("print", PrintVersionTask::class.java)
        val handler = PrintTaskRegisterHandler(task)
        
        handler.handle()
        
        assertEquals(File(project.projectDir, "version.properties"), task.outputFile.get().asFile)
        assertEquals(File(project.projectDir, "version.properties"), task.inputFile.get().asFile)
    }

    @Test
    fun `IncrementTaskRegisterHandler should configure input and output files`() {
        val task = project.tasks.create("increment", IncrementVersionTask::class.java)
        val handler = IncrementTaskRegisterHandler(task)
        
        handler.handle()
        
        assertEquals(File(project.projectDir, "version.properties"), task.outputFile.get().asFile)
        assertEquals(File(project.projectDir, "version.properties"), task.inputFile.get().asFile)
    }

    @Test
    fun `PromoteTaskRegisterHandler should configure input and output files`() {
        val task = project.tasks.create("promote", PromoteVersionTask::class.java)
        val handler = PromoteTaskRegisterHandler(task)
        
        handler.handle()
        
        assertEquals(File(project.projectDir, "version.properties"), task.outputFile.get().asFile)
        assertEquals(File(project.projectDir, "version.properties"), task.inputFile.get().asFile)
    }

    @Test
    fun `InitializeTaskRegisterHandler should configure output file only`() {
        val task = project.tasks.create("init", InitializeVersionTask::class.java)
        val handler = InitializeTaskRegisterHandler(task)
        
        handler.handle()
        
        assertEquals(File(project.projectDir, "version.properties"), task.outputFile.get().asFile)
        // inputFile should not be set because initializeInput is false
        assertNull(task.inputFile.orNull)
    }
}
