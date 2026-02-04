package com.napos.version.tasks.handlers

import com.napos.version.tasks.IncrementVersionTask
import com.napos.version.tasks.InitializeVersionTask
import com.napos.version.tasks.PrintVersionTask
import com.napos.version.tasks.PromoteVersionTask
import com.napos.version.tasks.VersionTask
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class TaskRegisterHandlerFactoryTest {

    private val project = ProjectBuilder.builder().build()

    @Test
    fun `should return PrintTaskRegisterHandler for PrintVersionTask`() {
        val task = project.tasks.create("print", PrintVersionTask::class.java)
        val handler = TaskRegisterHandlerFactory.newInstance(task)
        assertTrue(handler is PrintTaskRegisterHandler)
    }

    @Test
    fun `should return IncrementTaskRegisterHandler for IncrementVersionTask`() {
        val task = project.tasks.create("increment", IncrementVersionTask::class.java)
        val handler = TaskRegisterHandlerFactory.newInstance(task)
        assertTrue(handler is IncrementTaskRegisterHandler)
    }

    @Test
    fun `should return InitializeTaskRegisterHandler for InitializeVersionTask`() {
        val task = project.tasks.create("init", InitializeVersionTask::class.java)
        val handler = TaskRegisterHandlerFactory.newInstance(task)
        assertTrue(handler is InitializeTaskRegisterHandler)
    }

    @Test
    fun `should return PromoteTaskRegisterHandler for PromoteVersionTask`() {
        val task = project.tasks.create("promote", PromoteVersionTask::class.java)
        val handler = TaskRegisterHandlerFactory.newInstance(task)
        assertTrue(handler is PromoteTaskRegisterHandler)
    }

    @Test
    fun `should throw exception for unknown task`() {
        abstract class UnknownTask : VersionTask()
        val task = project.tasks.create("unknown", UnknownTask::class.java)
        
        assertThrows(Exception::class.java) {
            TaskRegisterHandlerFactory.newInstance(task)
        }
    }
}
