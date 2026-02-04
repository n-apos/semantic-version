package com.napos.version.data.models

import com.napos.version.data.models.Increment.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class VersionTest {

    @Test
    fun `should increment major version correctly`() {
        val version = Version(major = 1, minor = 2, patch = 3)
        version.update(MAJOR)
        
        assertEquals(2, version.major)
        assertEquals(0, version.minor)
        assertEquals(0, version.patch)
        assertEquals(Suffix.NONE, version.suffix.suffix)
    }

    @Test
    fun `should increment minor version correctly`() {
        val version = Version(major = 1, minor = 2, patch = 3)
        version.update(MINOR)
        
        assertEquals(1, version.major)
        assertEquals(3, version.minor)
        assertEquals(0, version.patch)
        assertEquals(Suffix.NONE, version.suffix.suffix)
    }

    @Test
    fun `should increment patch version correctly`() {
        val version = Version(major = 1, minor = 2, patch = 3)
        version.update(PATCH)
        
        assertEquals(1, version.major)
        assertEquals(2, version.minor)
        assertEquals(4, version.patch)
        assertEquals(Suffix.NONE, version.suffix.suffix)
    }

    @Test
    fun `should reset suffix when incrementing major`() {
        val version = Version(major = 1, minor = 0, patch = 0, suffix = VersionSuffix(Suffix.ALPHA, 1))
        version.update(MAJOR)
        
        assertEquals(2, version.major)
        assertEquals(Suffix.NONE, version.suffix.suffix)
    }
}
