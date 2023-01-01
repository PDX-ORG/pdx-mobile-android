package io.github.lexadiky.pdx.lib.blogger

object BLogger : ContextualLoggerDelegate {

    private val delegate: LoggerDelegate = LogcatLoggerDelegate()

    override fun log(level: LoggerLevel, tag: String?, message: String, throwable: Throwable?) {
        delegate.log(level, tag, message, throwable)
    }

    override fun tag(tag: String) = apply {
        delegate.tag(tag)
    }
}