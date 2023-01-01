package io.github.lexadiky.pdx.plugin.eve.domain

import io.github.lexadiky.pdx.plugin.eve.entity.EveEvent
import io.github.lexadiky.pdx.plugin.eve.entity.EveEventArgument
import io.github.lexadiky.pdx.plugin.eve.entity.EveModule
import io.github.lexadiky.pdx.plugin.eve.entity.EveType

class EveParser {

    fun parse(name: String, source: Map<String, Any>): EveModule {
        return EveModule(
            name = name,
            events = source.entries.map(::parseEvent)
        )
    }

    private fun parseEvent(entry: Map.Entry<String, Any>): EveEvent {
        return EveEvent(
            name = entry.key.removePrefix(EVENT_KEYWORD + KEYWORD_SEPARATOR),
            arguments = parseArguments(
                entry.value.mapGet(ARGUMENTS_KEYWORD) as Map<String, Any>
            )
        )
    }

    private fun parseArguments(data: Map<String, Any>): List<EveEventArgument> {
        return data.keys.map { it.split(KEYWORD_SEPARATOR) }
            .map { (typeStr, name) ->
                EveEventArgument(
                    name = name,
                    type = EveType.values().first { it.codeName == typeStr }
                )
            }
    }

    private fun Any.mapGet(name: String): Any = (this as Map<String, Any>)[name]!!

    companion object {

        private const val ARGUMENTS_KEYWORD = "arguments"
        private const val EVENT_KEYWORD = "event"
        private const val KEYWORD_SEPARATOR = ":"
    }
}