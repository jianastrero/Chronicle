/*
 * MIT License
 *
 * Copyright (c) 2019 Jian James Astrero
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.jianastrero.chronicle

import android.util.Log
import com.jianastrero.chronicle.exceptions.UnknownSeverityException
import com.jianastrero.chronicle.stories.Story

object Chronicle {
    /**
     * Values declaration and initializatiom
     */
    private val chronicleObjectRegex = Regex("com\\.jianastrero\\.chronicle\\..*");


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

    fun <T> v(message: T?) {
        Log.VERBOSE.log(message)
    }

    fun <T> d(message: T?) {
        Log.DEBUG.log(message)
    }

    /**
     * Protected Methods
     */


    /**
     * Private Methods
     */
    private fun <T> Int.log(message: T?) {
        story?.let { story ->
            val msg: String? = if (message is String) message else message?.toString()
            val throwable: Throwable? = if (message is Throwable) message else null

            if (story.log(this, msg, throwable)) {
                when (message) {
                    is String -> getStringLogger().invoke(getTag(), message)
                    is Throwable -> getThrowableLogger().invoke(getTag(), message.message, message)
                }
            }
        }
    }
    private fun Int.getStringLogger(): (String, String?) -> Int {
        return when (this) {
            Log.VERBOSE -> Log::v
            Log.DEBUG -> Log::d
            Log.INFO -> Log::i
            Log.WARN -> Log::w
            Log.ERROR -> Log::e
            Log.ASSERT -> Log::wtf
            else -> throw UnknownSeverityException(this)
        }
    }

    private fun Int.getThrowableLogger(): (String, String?, Throwable?) -> Int {
        return when (this) {
            Log.VERBOSE -> Log::v
            Log.DEBUG -> Log::d
            Log.INFO -> Log::i
            Log.WARN -> Log::w
            Log.ERROR -> Log::e
            Log.ASSERT -> Log::wtf
            else -> throw UnknownSeverityException(this)
        }
    }

    /**
     * [
     *      dalvik.system.VMStack.getThreadStackTrace(Native Method),
     *      java.lang.Thread.getStackTrace(Thread.java:1566),
     *      com.jianastrero.chronicle.Chronicle.getKey(Chronicle.kt:54),
     *      com.jianastrero.chronicle.Chronicle.d(Chronicle.kt:40),
     *      com.jianastrero.chroniclesample.MainActivity.onCreate(MainActivity.kt:14),
     *      android.app.Activity.performCreate(Activity.java:6679),
     *      android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1118),
     *      android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2618),
     *      android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:2726),
     *      android.app.ActivityThread.-wrap12(ActivityThread.java),
     *      android.app.ActivityThread$H.handleMessage(ActivityThread.java:1477),
     *      android.os.Handler.dispatchMessage(Handler.java:102),
     *      android.os.Looper.loop(Looper.java:154),
     *      android.app.ActivityThread.main(ActivityThread.java:6119),
     *      java.lang.reflect.Method.invoke(Native Method),
     *      com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:886),
     *      com.android.internal.os.ZygoteInit.main(ZygoteInit.java:776)
     * ]
     */
    private fun getTag(): String {
        val stackTrace = Thread.currentThread().stackTrace

        stackTrace.forEachIndexed { index, element ->
            if (index > 1 && !chronicleObjectRegex.matches(element.className))
                return "${element.fileName}:${element.lineNumber}(${element.methodName})"
        }

        return "Chronicle(unknown)"
    }
}