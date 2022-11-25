package io.github.lexadiky.pdx.lib

sealed interface FeatureToggle<Value: Any> {

    val id: String

    val group: String

    val defaultValue: Value
}
