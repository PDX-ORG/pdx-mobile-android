package io.github.lexadiky.pdx.lib.blogger

import android.util.Log

internal class LogcatLoggerDelegate : ContextualLoggerDelegate {

    private val tagHolder: ThreadLocal<String?> = ThreadLocal<String?>().apply {
        set(null)
    }

    override fun log(level: LoggerLevel, tag: String?, message: String, throwable: Throwable?) {
        val priority = when (level) {
            LoggerLevel.VERBOSE -> Log.VERBOSE
            LoggerLevel.DEBUG -> Log.DEBUG
            LoggerLevel.WARNING -> Log.WARN
            LoggerLevel.ERROR -> Log.ERROR
            LoggerLevel.ASSERT -> Log.ASSERT
            LoggerLevel.INFO -> Log.INFO
        }
        val actualTag = tag ?: tagHolder.consume()
        if (priority != Log.ERROR) {
            Log.println(priority, actualTag, message)
        } else {
            Log.e(actualTag, message, throwable)
        }
    }

    override fun tag(tag: String) = apply {
        tagHolder.set(tag)
    }

    private fun <T> ThreadLocal<T>.consume(): T? = get().also { set(null) }
}