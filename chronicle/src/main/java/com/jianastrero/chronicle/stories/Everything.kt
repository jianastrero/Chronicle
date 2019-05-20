package com.jianastrero.chronicle.stories

import android.util.Log

class Everything : Story {

    init {

    }

    override fun log(severity: Int, message: String?, throwable: Throwable?): Boolean {
        return severity >= Log.VERBOSE
    }
}