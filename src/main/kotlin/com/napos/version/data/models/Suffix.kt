package com.napos.version.data.models

enum class Suffix(val hierarchy: Int, val label: String) {
    SNAPSHOT(0, "SNAPSHOT"),
    ALPHA(1, "alpha"),
    BETA(2, "beta"),
    CANARY(3, "canary"),
    RC(4, "rc"),
    NONE(5, ""),
    ;

    fun isSerial(): Boolean =
        this in SERIALS

    companion object {

        private val SERIALS = arrayOf(
            ALPHA,
            BETA,
            CANARY,
            RC,
        )

        fun fromString(label: String): Suffix =
            Suffix.values().firstOrNull { it.label == label } ?: NONE

        fun fromHierarchy(hierarchy: Int): Suffix =
            Suffix.values().firstOrNull { it.hierarchy == hierarchy } ?: NONE
    }
}
