package io.github.lexadiky.pdx.plugin.eve

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.findByType
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import org.jetbrains.kotlin.gradle.model.KotlinAndroidExtension
import java.io.File

class EvePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        val inputFile = target.objects.fileProperty()
        inputFile.set(File(target.projectDir, "analytics"))

        val outputFile = target.objects.fileProperty()
        outputFile.set(File(target.buildDir, "generated/sources/main/kotlin/eve"))

        target.tasks.register("eveBuild", EveBuildTask::class.java) {
            input.set(inputFile)
            output.set(outputFile)
        }
        target.tasks.findByName("preBuild")
            ?.dependsOn("eveBuild")

        target.extensions.findByType<KotlinProjectExtension>()!!.apply {
            sourceSets.getByName("main") {
                kotlin.srcDir("build/generated/sources/main/kotlin/eve")
            }
            sourceSets.getByName("test") {
                kotlin.srcDir("build/generated/sources/main/kotlin/eve")
            }
        }
    }
}