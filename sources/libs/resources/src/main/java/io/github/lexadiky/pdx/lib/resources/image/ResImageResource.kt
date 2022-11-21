package io.github.lexadiky.pdx.lib.resources.image

class ResImageResource(val id: Int) : ImageResource

fun ImageResource.Companion.from(id: Int): ImageResource = ResImageResource(id)
