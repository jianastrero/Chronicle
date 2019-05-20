package com.jianastrero.chronicle

import android.util.Log
import com.jianastrero.chronicle.stories.Story

object Chronicle {
    /**
     * Values declaration and initializatiom
     */
    private val chronicleObjectRegex = Regex("com\\.jianastrero\\.chronicle\\.Chronicle\\..*");


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
            if (it.log(Log.DEBUG, throwable))
                Log.d(getTag(), throwable?.message, throwable)
        }
    }

    fun d(message: String?) {
        story?.let {
            if (it.log(Log.DEBUG, Throwable(message)))
                Log.d(getTag(), message)
        }
    }


    /**
     * Protected Methods
     */


    /**
     * Private Methods
     */

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
            Log.d("JIAN TEST", "className: " + element.className)
            if (index > 1 && !chronicleObjectRegex.matches(element.className))
                return "${element.fileName}:${element.lineNumber}(${element.methodName})"
        }

        return "Chronicle(unknown"
    }
}