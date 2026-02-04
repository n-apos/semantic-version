package com.napos.version.data.models

import com.napos.version.data.models.Increment.SUFFIX
import com.napos.version.data.models.Increment.SUFFIX_INCREMENT
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class VersionSuffixTest {

    @Test
    fun `should cycle through suffixes correctly`() {
        val suffix = VersionSuffix(Suffix.NONE, Int.MIN_VALUE)
        
        // NONE -> SNAPSHOT
        suffix.update(SUFFIX)
        assertEquals(Suffix.SNAPSHOT, suffix.suffix)
        
        // SNAPSHOT -> ALPHA
        suffix.update(SUFFIX)
        assertEquals(Suffix.ALPHA, suffix.suffix)
        
        // ALPHA -> BETA
        suffix.update(SUFFIX)
        assertEquals(Suffix.BETA, suffix.suffix)
    }

    @Test
    fun `should increment suffix number correctly`() {
        val suffix = VersionSuffix(Suffix.ALPHA, 1)
        
        suffix.update(SUFFIX_INCREMENT)
        assertEquals(2, suffix.increment)
    }

    @Test
    fun `should initialize increment when switching to serial suffix`() {
        val suffix = VersionSuffix(Suffix.SNAPSHOT, Int.MIN_VALUE)
        
        // SNAPSHOT -> ALPHA (Serial)
        suffix.update(SUFFIX)
        assertEquals(Suffix.ALPHA, suffix.suffix)
        assertEquals(1, suffix.increment)
    }
}
