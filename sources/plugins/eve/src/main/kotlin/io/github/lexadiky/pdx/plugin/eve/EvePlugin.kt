package io.github.lexadiky.pdx.plugin.eve

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import java.io.File

class EvePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        val inputFile = target.objects.fileProperty()
        inputFile.set(File(target.projectDir, "analytics"))

        val outputFileProperty = target.objects.fileProperty()
        val outputFile = File(target.buildDir, "generated/sources/main/kotlin/eve")
        outputFileProperty.set(outputFile)

        target.tasks.register("eveBuild", EveBuildTask::class.java) {
            input.set(inputFile)
            output.set(outputFileProperty)
        }
        target.tasks.findByName("preBuild")
            ?.dependsOn("eveBuild")

        target.extensions.findByType<KotlinProjectExtension>()!!.apply {
            sourceSets.getByName("main") {
                kotlin.srcDir(outputFile)
            }
            sourceSets.getByName("test") {
                kotlin.srcDir(outputFile)
            }
        }
    }
}