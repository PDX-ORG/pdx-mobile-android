@file:OptIn(ExperimentalContracts::class)

package io.github.lexadiky.pdx.lib.blogger

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

interface LoggerDelegate {

    fun log(level: LoggerLevel, tag: String?, message: String, throwable: Throwable?)

    fun tag(tag: String): ContextualLoggerDelegate
}
