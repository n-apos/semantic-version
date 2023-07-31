package com.napos.versioning.models

@Suppress("EqualsOrHashCode")
data class Version(
    val major: Int,
    val minor: Int,
    val patch: Int,
    val additional: Int = Int.MIN_VALUE,
    val suffix: VersionSuffix?,
) : Comparable<Version> {

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
                suffix?.compareTo(other.suffix) ?: -(other.suffix?.compareTo(suffix) ?: 0)
            )

    override fun toString(): String =
        "$major.$minor.$patch${
            if (additional >= 0) ".$additional" else ""
        }${
            suffix?.let { "-$it" } ?: ""
        }"
}
