package com.jianastrero.chronicle.stories

interface Story {
    fun log(priority: Int, message: String?, throwable: Throwable?): Boolean
}