package com.jianastrero.chroniclesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jianastrero.chronicle.Chronicle
import com.jianastrero.chroniclesample.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Chronicle.d("Hello Errors")
    }
}
