package com.jianastrero.chronicle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jianastrero.chroniclesample.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Chronicle.d(Throwable("Hello Errors"))
    }
}
