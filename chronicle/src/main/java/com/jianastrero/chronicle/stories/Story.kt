package com.jianastrero.chronicle.stories

interface Story {
    /**
     * @param priority The severity / priority of the log. Check out [android.util.Log]
     * @param message The log message. If this is null, throwable is not
     * @param throwable The log throwable. If this is null, message is not
     *
     * @return true if this should be logged and false if not
     */
    fun log(severity: Int, message: String?, throwable: Throwable?): Boolean
}