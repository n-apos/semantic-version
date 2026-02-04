package com.napos.version.tasks.managers

import com.napos.version.configuration.VersionPluginExtension
import com.napos.version.tasks.PrintVersionTask
import com.napos.version.util.constants.EXTENSION_NAME
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TaskManagerTest {

    @Test
    fun `should return singleton instance`() {
        val instance1 = TaskManager.getInstance()
        val instance2 = TaskManager.getInstance()

        assertSame(instance1, instance2)
    }

    @Test
    fun `should register tasks to project`() {
        val project = ProjectBuilder.builder().build()
        
        // Create the extension required by the tasks
        project.extensions.create(EXTENSION_NAME, VersionPluginExtension::class.java)
        
        val taskManager = TaskManager.getInstance()
        
        taskManager.add(PrintVersionTask::class.java)
        taskManager.register(project)
        
        val task = project.tasks.findByName("printVersion")
        assertNotNull(task)
        assertTrue(task is PrintVersionTask)
    }
}
