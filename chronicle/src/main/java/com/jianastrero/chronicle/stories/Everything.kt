package com.jianastrero.chronicle.stories

import android.util.Log

class Everything : Story {

    init {

    }

    override fun log(priority: Int, message: String?, throwable: Throwable?): Boolean {
        return priority >= Log.VERBOSE
    }
}