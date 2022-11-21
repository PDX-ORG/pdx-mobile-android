package io.github.lexadiky.pdx.lib.resources.string

import androidx.annotation.StringRes

class LiteralStringResource(val value: String) : StringResource

fun StringResource.Companion.from(text: String): StringResource {
    return LiteralStringResource(text)
}
