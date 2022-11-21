package io.github.lexadiky.pdx.lib.resources.string

import kotlinx.datetime.Instant

class TemporalStringResource(val instant: Instant) : StringResource

fun StringResource.Companion.from(instant: Instant): StringResource
    = TemporalStringResource(instant)