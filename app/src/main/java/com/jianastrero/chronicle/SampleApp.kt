package com.jianastrero.chronicle

import android.app.Application
import com.jianastrero.chronicle.stories.Everything

class SampleApp : Application() {
    override fun onCreate() {
        super.onCreate()

        Chronicle.of(Everything())
    }
}