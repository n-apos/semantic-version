package com.napos.version.data.models

import com.napos.version.data.Updatable
import com.napos.version.data.models.Increment.SUFFIX
import com.napos.version.data.models.Increment.SUFFIX_INCREMENT

data class VersionSuffix(
    var suffix: Suffix,
    var increment: Int,
) : Comparable<VersionSuffix?>,
    Updatable<Increment> {

    init {
        validate()
    }

    override fun update(type: Increment) {
        when (type) {
            SUFFIX -> {
                suffix = Suffix.fromHierarchy((suffix.hierarchy + 1) % Suffix.values().size)
                increment = if (suffix.isSerial())
                    1
                else
                    Int.MIN_VALUE
            }

            SUFFIX_INCREMENT ->
                when (increment) {
                    in Int.MIN_VALUE until 0 -> increment = 0
                    else -> increment++
                }

            else -> Unit
        }

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

    // ----
    fun reset() {
        suffix = Suffix.NONE
        increment = Int.MIN_VALUE
        validate()
    }

    // Ensure that the increment is rightly set
    private fun validate() {
        increment = when {
            suffix.isSerial() ->
                when (increment) {
                    in Int.MIN_VALUE..0 -> 1
                    else -> increment
                }

            else -> Int.MIN_VALUE
        }
    }

}
