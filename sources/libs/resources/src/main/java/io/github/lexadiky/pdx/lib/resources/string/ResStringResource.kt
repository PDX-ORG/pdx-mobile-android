package io.github.lexadiky.pdx.lib.resources.string

import androidx.annotation.StringRes

class ResStringResource(@StringRes val stringRes: Int) : StringResource

fun StringResource.Companion.from(@StringRes res: Int): StringResource {
    return ResStringResource(res)
}
