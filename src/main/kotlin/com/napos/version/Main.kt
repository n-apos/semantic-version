package com.napos.version

import com.napos.version.models.Suffix
import com.napos.version.models.Version
import com.napos.version.models.VersionSuffix

fun main() {
    val version = Version(
        major = 2,
        minor = 0,
        patch = 20,
        additional = 1,
        suffix = VersionSuffix(
            suffix = Suffix.SNAPSHOT,
            number = Int.MIN_VALUE,
        ),
    )
    println(version)
}