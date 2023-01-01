@file:OptIn(ExperimentalContracts::class)

package io.github.lexadiky.pdx.lib.blogger

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

interface ContextualLoggerDelegate : LoggerDelegate

fun ContextualLoggerDelegate.verbose(message: String) {
    log(LoggerLevel.VERBOSE, null, message, null)
}

fun ContextualLoggerDelegate.debug(message: String) {
    log(LoggerLevel.DEBUG, null, message, null)
}

fun ContextualLoggerDelegate.info(message: String) {
    log(LoggerLevel.INFO, null, message, null)
}

fun ContextualLoggerDelegate.warning(message: String) {
    log(LoggerLevel.WARNING, null, message, null)
}

fun ContextualLoggerDelegate.error(message: String, throwable: Throwable? = null) {
    log(LoggerLevel.ERROR, null, message, throwable)
}

fun ContextualLoggerDelegate.assert(message: String, condition: Boolean) {
    contract {
        returns() implies condition
    }
    log(LoggerLevel.ASSERT, null, message, AssertionError(message))
}
