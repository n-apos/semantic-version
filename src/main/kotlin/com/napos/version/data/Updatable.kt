package com.napos.version.data

interface Updatable<in Type> {
    fun update(type: Type)
}