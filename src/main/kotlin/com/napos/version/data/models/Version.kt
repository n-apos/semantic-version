package com.napos.version.data.models

import com.napos.version.data.Promotable
import com.napos.version.data.Updatable
import com.napos.version.data.models.Increment.*

data class Version(
    var major: Int = 0,
    var minor: Int = 0,
    var patch: Int = 0,
    var additional: Int = Int.MIN_VALUE,
    var suffix: VersionSuffix = VersionSuffix(
        suffix = Suffix.NONE,
        increment = Int.MIN_VALUE,
    ),
) : Comparable<Version>,
    Updatable<Increment>,
    Promotable {

    override fun update(type: Increment) {
        when (type) {
            MAJOR -> {
                major++
                suffix.reset()
            }

            MINOR -> {
                minor++
                suffix.reset()
            }

            PATCH -> {
                patch++
                suffix.reset()
            }

            ADDITIONAL -> {
                when (additional) {
                    in Int.MIN_VALUE until 0 -> additional = 0
                    else -> additional++
                }
                suffix.reset()
            }

            SUFFIX, SUFFIX_INCREMENT -> suffix.update(type)
        }
    }

    override fun promote() {
        suffix.reset()
    }

    override fun equals(other: Any?): Boolean =
        (other as? Version)
            ?.let {
                compareTo(it) == 0
            } ?: false

    override fun hashCode(): Int {
        var result = major.hashCode()
        result = 31 * result + minor.hashCode()
        result = 31 * result + patch.hashCode()
        result = 31 * result + additional.hashCode()
        result = 31 * result + suffix.hashCode()
        return result
    }

    override fun compareTo(other: Version): Int =
        (major - other.major)
            .or(minor - other.minor)
            .or(patch - other.patch)
            .or(additional - other.additional)
            .or(
                // Comparison of two object that avoids if-null notation
                suffix.compareTo(other.suffix)
            )

    override fun toString(): String =
        "$major.$minor.$patch${
            if (additional >= 0) ".$additional" else ""
        }${
            if (suffix.suffix != Suffix.NONE) "-$suffix" else ""
        }"

}
