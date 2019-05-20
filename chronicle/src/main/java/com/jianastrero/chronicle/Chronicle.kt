package com.jianastrero.chronicle

import android.util.Log
import com.jianastrero.chronicle.stories.Everything
import com.jianastrero.chronicle.stories.Story

object Chronicle {
    /**
     * Values declaration and initializatiom
     */


    /**
     * Variables declaration and initialization
     */
    private var story: Story = Everything()


    /**
     * Object constructor initialization
     */
    init {

    }


    /**
     * Overridden Methods
     */


    /**
     * Public Methods
     */
    fun d(throwable: Throwable?) {
        getKey()
    }


    /**
     * Protected Methods
     */


    /**
     * Private Methods
     */
    private fun getKey(): String {
        val stackTrace = Thread.currentThread().stackTrace;
        Log.d("JIAN", stackTrace.contentDeepToString())
        return "";
    }
}