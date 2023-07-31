package com.napos.version.models

enum class Suffix(val hierarchy: Int, val label: String) {
    SNAPSHOT(0, "SNAPSHOT"),
    ALPHA(1, "alpha"),
    BETA(2, "beta"),
    CANARY(3, "canary"),
    RC(4, "rc"),
}
