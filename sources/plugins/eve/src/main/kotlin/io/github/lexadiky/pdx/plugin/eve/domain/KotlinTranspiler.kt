package io.github.lexadiky.pdx.plugin.eve.domain

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import io.github.lexadiky.pdx.plugin.eve.entity.EveEvent
import io.github.lexadiky.pdx.plugin.eve.entity.EveEventArgument
import io.github.lexadiky.pdx.plugin.eve.entity.EveModule
import io.github.lexadiky.pdx.plugin.eve.entity.EveType
import io.github.lexadiky.pdx.plugin.eve.entity.TranspirationOutput
import org.gradle.configurationcache.extensions.capitalized
import java.lang.StringBuilder

class KotlinTranspiler {

    private val analyticsManagerClass = ClassName.bestGuess(ANALYTICS_MANAGER_CLASS)
    private val stringClassName = ClassName.bestGuess(TYPE_STRING)

    fun transpile(module: EveModule): TranspirationOutput {
        val stringBuilder = StringBuilder()
        FileSpec.builder(PACKAGE, formatClassName(module))
            .addFileComment(GENERATED_COMMENT)
            .addType(transpileToType(module))
            .build()
            .writeTo(stringBuilder)

        return TranspirationOutput(
            file = formatClassName(module) + KT_EXTENSION,
            source = stringBuilder.toString()
        )
    }

    private fun transpileToType(module: EveModule): TypeSpec {
        val builder = TypeSpec.classBuilder(formatClassName(module))

        // add analytics manager via constructor
        builder.primaryConstructor(
            FunSpec.constructorBuilder()
                .addParameter(ANALYTICS_MANAGER_PROPERTY, analyticsManagerClass)
                .build()
        )

        builder.addProperty(
            PropertySpec.builder(ANALYTICS_MANAGER_PROPERTY, analyticsManagerClass)
                .initializer(ANALYTICS_MANAGER_PROPERTY)
                .addModifiers(KModifier.PRIVATE)
                .build()
        )

        module.events.forEach { event ->
            builder.addFunction(
                makeEventFunction(event)
            )
        }

        return builder.build()
    }

    private fun makeEventFunction(event: EveEvent): FunSpec {
        val funBuilder = FunSpec.builder(snakeToCamel(event.name, false))

        event.arguments.forEach { argument ->
            funBuilder.addParameter(snakeToCamel(argument.name, false), argument.typeClassName())
        }

        funBuilder.addCode("analyticsManager.log(event=\"${event.name}\", " +
                "parameters=mapOf(${event.arguments.joinToString { "\"${it.name}\" to ${snakeToCamel(it.name, false)}" }}))")

        return funBuilder.build()
    }

    private fun EveEventArgument.typeClassName(): ClassName = when (type) {
        EveType.STRING -> stringClassName
    }

    private fun formatClassName(module: EveModule) =
        snakeToCamel(module.name, true) + CLASS_APPENDIX

    private fun snakeToCamel(text: String, capital: Boolean): String {
        return if (capital) {
            text.split(SNAKE_CASE_SEPARATOR)
                .joinToString(separator = "") { it.capitalized() }
        } else {
            val split = text.split(SNAKE_CASE_SEPARATOR)
            split.first() + split.drop(1)
                .joinToString(separator = "") { it.capitalized() }
        }
    }

    companion object {

        private const val KT_EXTENSION = ".kt"
        private const val CLASS_APPENDIX = "EventsSpec"
        private const val PACKAGE = "io.github.lexadiky.pdx.generated.analytics"
        private const val GENERATED_COMMENT = "GENERATED"
        private const val SNAKE_CASE_SEPARATOR = "_"
        private const val ANALYTICS_MANAGER_CLASS = "io.github.lexadiky.pdx.lib.analytics.AnalyticsManager"
        private const val ANALYTICS_MANAGER_PROPERTY = "analyticsManager"
        private const val TYPE_STRING = "kotlin.String"
    }
}