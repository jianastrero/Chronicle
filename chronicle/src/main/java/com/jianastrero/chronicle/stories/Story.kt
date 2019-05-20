package com.jianastrero.chronicle.stories

interface Story {
    fun log(priority: Int, throwable: Throwable?): Boolean
}