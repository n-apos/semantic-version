package com.napos.version.data.mappers

import com.napos.version.data.models.Suffix
import com.napos.version.data.models.Version
import com.napos.version.data.models.VersionSuffix
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

class VersionPropertiesMapperTest {

    private val mapper = VersionPropertiesMapper()

    @Test
    fun `should map version to properties`() {
        val version = Version(
            major = 1,
            minor = 2,
            patch = 3,
            suffix = VersionSuffix(Suffix.ALPHA, 1)
        )

        val props = mapper.from(version)

        assertEquals("1", props.getProperty("major"))
        assertEquals("2", props.getProperty("minor"))
        assertEquals("3", props.getProperty("patch"))
        assertEquals("alpha", props.getProperty("suffix"))
        assertEquals("1", props.getProperty("suffixIncrement"))
    }

    @Test
    fun `should map properties to version`() {
        val props = Properties().apply {
            setProperty("major", "1")
            setProperty("minor", "2")
            setProperty("patch", "3")
            setProperty("suffix", "beta")
            setProperty("suffixIncrement", "2")
        }

        val version = mapper.to(props)

        assertEquals(1, version.major)
        assertEquals(2, version.minor)
        assertEquals(3, version.patch)
        assertEquals(Suffix.BETA, version.suffix.suffix)
        assertEquals(2, version.suffix.increment)
    }
}
