package com.napos.version.data

import com.napos.version.data.models.Increment

interface Promotable {

    fun promote(type: Increment)
}