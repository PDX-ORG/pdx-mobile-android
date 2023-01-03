package io.github.lexadiky.pdx.ui.uikit.util

import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.format
import io.github.lexadiky.pdx.lib.resources.string.from
import io.github.lexadiky.pdx.lib.uikit.R

object UikitStringFormatter {

    fun nationalId(id: Int): StringResource =
        StringResource.from(R.string. uikit_national_id_template)
            .format(id)
}