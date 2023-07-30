package com.napos.versioning.models

enum class Suffix(val label: String) {
    SNAPSHOT("SNAPSHOT"),
    ALPHA("alpha"),
    BETA("beta"),
    CANARY("canary"),
    RC("rc"),
}
