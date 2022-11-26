package io.github.lexadiky.pdx.domain.achievement.library

import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.string.StringResource

interface Achievement {

    val id: String
    val name: StringResource
    val description: StringResource
    val icon: ImageResource?
}