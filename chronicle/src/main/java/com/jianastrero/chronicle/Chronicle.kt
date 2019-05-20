package com.jianastrero.chronicle

import android.util.Log
import com.jianastrero.chronicle.stories.Story

object Chronicle {
    /**
     * Values declaration and initializatiom
     */


    /**
     * Variables declaration and initialization
     */
    private var story: Story? = null


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
    fun of(story: Story?) {
        this.story = story
    }

    fun d(throwable: Throwable?) {
        story?.let {
            getKey()
        }
    }


    /**
     * Protected Methods
     */


    /**
     * Private Methods
     */
    private fun getKey(): String {
        val stackTrace = Thread.currentThread().stackTrace;
        Log.d("JIAN TEST", stackTrace.contentDeepToString())
        return "";
    }
}