package com.napos.versioning.models

data class VersionSuffix(
    val suffix: Suffix,
    val number: Int,
) : Comparable<VersionSuffix?> {

    override fun compareTo(other: VersionSuffix?): Int =
        other?.let {
            (suffix.hierarchy - it.suffix.hierarchy)
                .or(number - it.number)
        } ?: -1

    override fun toString(): String =
        "${suffix.label}${
            if (number in 0..99) String.format("%.2f", number) else ""
        }"
}
