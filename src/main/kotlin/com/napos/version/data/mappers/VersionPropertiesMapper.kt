package com.napos.version.data.mappers

import com.napos.version.data.models.Suffix
import com.napos.version.data.models.Version
import com.napos.version.data.models.VersionSuffix
import java.util.*

class VersionPropertiesMapper {

    fun from(version: Version): Properties = Properties()
        .apply {
            setProperty(KEY_MAJOR, version.major.toString())
            setProperty(KEY_MINOR, version.minor.toString())
            setProperty(KEY_PATCH, version.patch.toString())
            if (version.additional >= 0) {
                setProperty(KEY_ADDITIONAL, version.additional.toString())
            }
            with(version.suffix) {
                if (suffix != Suffix.NONE) {
                    setProperty(KEY_SUFFIX, suffix.label)
                    if (increment >= 0) {
                        setProperty(KEY_SUFFIX_INCREMENT, increment.toString())
                    }
                }
            }
        }


    fun to(props: Properties): Version {
        val additional = if (props.containsKey(KEY_ADDITIONAL))
            props.getProperty(KEY_ADDITIONAL)
                .toInt()
        else
            Int.MIN_VALUE

        val suffix = if (props.containsKey(KEY_SUFFIX))
            VersionSuffix(
                suffix = Suffix.fromString(props.getProperty(KEY_SUFFIX)),
                increment = if (props.containsKey(KEY_SUFFIX_INCREMENT))
                    props.getProperty(KEY_SUFFIX_INCREMENT)
                        .toInt()
                else
                    Int.MIN_VALUE,
            )
        else VersionSuffix(
            suffix = Suffix.NONE,
            increment = Int.MIN_VALUE,
        )

        return Version(
            major = props.getProperty(KEY_MAJOR)
                .toInt(),
            minor = props.getProperty(KEY_MINOR)
                .toInt(),
            patch = props.getProperty(KEY_PATCH)
                .toInt(),
            additional = additional,
            suffix = suffix,
        )
    }

    companion object {
        const val KEY_MAJOR = "major"
        const val KEY_MINOR = "minor"
        const val KEY_PATCH = "patch"
        const val KEY_ADDITIONAL = "additional"
        const val KEY_SUFFIX = "suffix"
        const val KEY_SUFFIX_INCREMENT = "suffixIncrement"
    }
}