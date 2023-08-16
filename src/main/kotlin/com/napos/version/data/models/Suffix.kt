package com.napos.version.data.models

enum class Suffix(val hierarchy: Int, val label: String) {
    SNAPSHOT(0, "SNAPSHOT"),
    ALPHA(1, "alpha"),
    BETA(2, "beta"),
    CANARY(3, "canary"),
    RC(4, "rc"),
    NONE(5, ""), ;

    companion object {
        fun fromString(label: String): Suffix =
            Suffix.values().firstOrNull { it.label == label } ?: NONE

        fun fromHierarchy(hierarchy: Int): Suffix =
            Suffix.values().firstOrNull { it.hierarchy == hierarchy } ?: NONE
    }
}
