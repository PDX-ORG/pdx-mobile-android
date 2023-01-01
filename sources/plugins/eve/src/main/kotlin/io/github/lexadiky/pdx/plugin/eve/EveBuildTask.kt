package io.github.lexadiky.pdx.plugin.eve

import io.github.lexadiky.pdx.plugin.eve.domain.EveParser
import io.github.lexadiky.pdx.plugin.eve.domain.KotlinTranspiler
import io.github.lexadiky.pdx.plugin.eve.domain.SourceReader
import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class EveBuildTask : DefaultTask() {

    @get:InputDirectory
    internal abstract val input: RegularFileProperty

    @get:OutputDirectory
    internal abstract val output: RegularFileProperty

    private val sourceReader = SourceReader()
    private val parser = EveParser()
    private val transpiler = KotlinTranspiler()

    @TaskAction
    fun action() {
        val sources = sourceReader.read(input.get().asFile)
        val modules = sources.map { parser.parse(it.first, it.second) }
        val transpiled = modules.map { transpiler.transpile(it) }
        transpiled.forEach { unit ->
            File(output.asFile.get(), unit.file)
                .writeText(unit.source)
        }
    }
}