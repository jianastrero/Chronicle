package com.jianastrero.chronicle.sample

import android.app.Application
import com.jianastrero.chronicle.Chronicle
import com.jianastrero.chronicle.stories.Everything

class SampleApp : Application() {
    override fun onCreate() {
        super.onCreate()

        Chronicle.of(Everything())
    }
}