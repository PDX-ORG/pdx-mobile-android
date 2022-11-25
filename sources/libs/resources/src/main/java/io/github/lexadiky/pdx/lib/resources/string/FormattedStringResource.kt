package io.github.lexadiky.pdx.lib.resources.string

class FormattedStringResource(val base: StringResource, vararg val arguments: Any) : StringResource

fun StringResource.format(vararg arguments: Any): StringResource {
    return FormattedStringResource(this, *arguments)
}
