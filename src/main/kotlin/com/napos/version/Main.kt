package com.napos.version

import com.napos.version.data.models.Increment
import com.napos.version.data.models.Suffix
import com.napos.version.data.models.Version
import com.napos.version.data.models.VersionSuffix

fun main() {
    val version = Version()
    version.suffix = VersionSuffix(
        suffix = Suffix.ALPHA,
        increment = 1
    )
    version.update(Increment.MINOR)
    println(version)
    version.update(Increment.SUFFIX)
    println(version)
    version.update(Increment.SUFFIX)
    println(version)
    version.update(Increment.SUFFIX_INCREMENT)
    println(version)
    version.update(Increment.SUFFIX)
    println(version)
}