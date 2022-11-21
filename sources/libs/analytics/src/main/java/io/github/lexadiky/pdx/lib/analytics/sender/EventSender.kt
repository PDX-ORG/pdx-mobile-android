package io.github.lexadiky.pdx.lib.analytics.sender

interface EventSender {

    fun log(event: String, parameters: Map<String, Any>)
}