package com.napos.versioning.models

data class Version(
    val major: Int,
    val minor: Int,
    val patch: Int,
    val additional: Int?,
    val suffix: VersionSuffix?,
)
