package io.github.lexadiky.pdx.feature.generic.list.entity

import androidx.annotation.ColorRes
import io.github.lexadiky.pdx.lib.resources.color.ColorResource
import io.github.lexadiky.pdx.lib.resources.image.ImageResource
import io.github.lexadiky.pdx.lib.resources.string.StringResource

interface GenericListItem {

    val id: String
    val title: StringResource
    val note: StringResource
    val primaryImage: ImageResource
    val secondaryImage: ImageResource?
    val tags: List<Tag>

    data class Tag(val text: StringResource, val color: ColorResource)
}