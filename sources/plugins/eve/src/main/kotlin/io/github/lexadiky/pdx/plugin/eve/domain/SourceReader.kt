package io.github.lexadiky.pdx.plugin.eve.domain

import org.yaml.snakeyaml.Yaml
import java.io.File

class SourceReader {
    private val snakeYaml = Yaml()

    fun read(directory: File): List<Pair<String, Map<String, Any>>> {
        return directory.walk().toList()
            .filter { it.name.endsWith(SOURCE_EXT) }
            .map { it.name.removeSuffix(SOURCE_EXT) to it.readText() }
            .map { (name, text) -> name to snakeYaml.load(text) }
    }

    companion object {

        private const val SOURCE_EXT = ".eve.yaml"
    }
}