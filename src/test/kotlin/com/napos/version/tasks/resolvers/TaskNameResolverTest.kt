package com.napos.version.tasks.resolvers

import com.napos.version.tasks.IncrementVersionTask
import com.napos.version.tasks.InitializeVersionTask
import com.napos.version.tasks.PrintVersionTask
import com.napos.version.tasks.PromoteVersionTask
import com.napos.version.tasks.VersionTask
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class TaskNameResolverTest {

    @Test
    fun `should resolve PrintVersionTask name`() {
        val name = TaskNameResolver.getName(PrintVersionTask::class.java)
        assertEquals("printVersion", name)
    }

    @Test
    fun `should resolve IncrementVersionTask name`() {
        val name = TaskNameResolver.getName(IncrementVersionTask::class.java)
        assertEquals("incrementVersion", name)
    }

    @Test
    fun `should resolve InitializeVersionTask name`() {
        val name = TaskNameResolver.getName(InitializeVersionTask::class.java)
        assertEquals("initializeVersion", name)
    }

    @Test
    fun `should resolve PromoteVersionTask name`() {
        val name = TaskNameResolver.getName(PromoteVersionTask::class.java)
        assertEquals("promoteVersion", name)
    }

    @Test
    fun `should throw exception for unknown task`() {
        abstract class UnknownTask : VersionTask()
        
        assertThrows(Exception::class.java) {
            TaskNameResolver.getName(UnknownTask::class.java)
        }
    }
}
