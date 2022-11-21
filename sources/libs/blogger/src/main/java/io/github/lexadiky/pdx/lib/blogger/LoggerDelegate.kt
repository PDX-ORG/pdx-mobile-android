@file:OptIn(ExperimentalContracts::class)

package io.github.lexadiky.pdx.lib.blogger

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

interface LoggerDelegate {

    fun log(level: LoggerLevel, tag: String?, message: String, throwable: Throwable?)

    fun tag(tag: String): LoggerDelegate
}

fun LoggerDelegate.verbose(message: String) {
    log(LoggerLevel.VERBOSE, null, message, null)
}

fun LoggerDelegate.debug(message: String) {
    log(LoggerLevel.DEBUG, null, message, null)
}

fun LoggerDelegate.info(message: String) {
    log(LoggerLevel.INFO, null, message, null)
}

fun LoggerDelegate.warning(message: String) {
    log(LoggerLevel.WARNING, null, message, null)
}

fun LoggerDelegate.error(message: String, throwable: Throwable? = null) {
    log(LoggerLevel.ERROR, null, message, throwable)
}

fun LoggerDelegate.assert(message: String, condition: Boolean) {
    contract {
        returns() implies condition
    }
    log(LoggerLevel.ASSERT, null, message, AssertionError(message))
}
