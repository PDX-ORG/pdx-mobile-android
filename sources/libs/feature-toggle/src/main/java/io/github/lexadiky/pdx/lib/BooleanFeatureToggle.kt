package io.github.lexadiky.pdx.lib

class BooleanFeatureToggle(
    override val id: String,
    override val group: String,
    override val defaultValue: Boolean
) : FeatureToggle<Boolean>
