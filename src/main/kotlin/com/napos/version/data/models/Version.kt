package com.napos.version.data.models

import com.napos.version.data.Promotable
import com.napos.version.data.Updatable
import com.napos.version.data.Upgradable

@Suppress("EqualsOrHashCode")
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
    Upgradable,
    Promotable {

    override fun update(type: Increment) {
        when (type) {
            Increment.MAJOR -> {
                major++
                suffix.reset()
            }

            Increment.MINOR -> {
                minor++
                suffix.reset()
            }

            Increment.PATCH -> {
                patch++
                suffix.reset()
            }

            Increment.ADDITIONAL -> {
                when (additional) {
                    in Int.MIN_VALUE until 0 -> additional = 0
                    else -> additional++
                }
                suffix.reset()
            }

            Increment.SUFFIX, Increment.SUFFIX_INCREMENT -> suffix.update(type)
        }
    }

    override fun upgrade() {
        when (suffix.suffix) {
            Suffix.NONE -> Unit
            else -> suffix.upgrade()
        }
    }

    override fun promote(type: Increment) {
        suffix.reset()
        update(type)
    }

    override fun equals(other: Any?): Boolean =
        (other as? Version)
            ?.let {
                compareTo(it) == 0
            } ?: false

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
