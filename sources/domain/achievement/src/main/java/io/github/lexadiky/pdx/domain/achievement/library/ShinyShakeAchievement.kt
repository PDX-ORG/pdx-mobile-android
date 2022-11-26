package io.github.lexadiky.pdx.domain.achievement.library

import io.github.lexadiky.pdx.domain.achievement.R
import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.string.StringResource
import io.github.lexadiky.pdx.lib.resources.string.from

class ShinyShakeAchievement : Achievement {

    override val id: String = "shiny_shake"
    override val name: StringResource = StringResource.from(R.string.achievement_lib_shiny_shake_name)
    override val description: StringResource = StringResource.from(R.string.achievement_lib_shiny_shake_description)
    override val icon: ImageResource? = null
}