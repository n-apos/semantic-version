package com.napos.version.util.extensions

import com.napos.version.data.mappers.VersionPropertiesMapper
import com.napos.version.data.models.Increment
import com.napos.version.data.models.Version
import java.io.File
import java.util.*

fun File.readVersion(): Version {
    val mapper = VersionPropertiesMapper()
    val reader = inputStream()

    val props = Properties()
    props.load(reader)
    reader.close()

    return mapper.to(props)
}

fun File.writeVersion(version: Version, comments: String = "") {
    val mapper = VersionPropertiesMapper()
    val writer = outputStream()
    mapper.from(version)
        .store(writer, comments)
}