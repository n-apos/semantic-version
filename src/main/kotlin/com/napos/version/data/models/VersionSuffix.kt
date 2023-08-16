package com.napos.version.data.models

import com.napos.version.data.Updatable
import com.napos.version.data.Upgradable

data class VersionSuffix(
    var suffix: Suffix,
    var increment: Int = Int.MIN_VALUE,
) : Comparable<VersionSuffix?>,
    Updatable<Increment>,
    Upgradable {

    override fun update(type: Increment) {
        when (type) {
            Increment.SUFFIX -> {
                suffix = Suffix.fromHierarchy(suffix.hierarchy + 1 % Suffix.values().size)
            }

            Increment.SUFFIX_INCREMENT ->
                when (increment) {
                    in Int.MIN_VALUE until 0 -> increment = 0
                    else -> increment++
                }
            else -> Unit
        }

    }

    override fun upgrade() {

    }

    override fun compareTo(other: VersionSuffix?): Int =
        other?.let {
            (suffix.hierarchy - it.suffix.hierarchy)
                .or(increment - it.increment)
        } ?: -1

    override fun toString(): String =
        "${suffix.label}${
            if (increment in 0..99)
                increment.toString()
                    .padStart(2, '0')
            else
                ""
        }"
}
