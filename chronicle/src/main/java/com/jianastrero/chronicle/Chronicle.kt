package com.jianastrero.chronicle

import android.util.Log

object Chronicle {
    /**
     * Values declaration and initializatiom
     */


    /**
     * Variables declaration and initialization
     */


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