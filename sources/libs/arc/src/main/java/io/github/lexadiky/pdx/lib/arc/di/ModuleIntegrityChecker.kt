package io.github.lexadiky.pdx.lib.arc.di

import java.lang.IllegalArgumentException
import kotlin.reflect.KClass
import kotlin.reflect.KVisibility

class ModuleIntegrityChecker(
    private val isEnabled: Boolean
) {

    fun <T: Any> check(cls: KClass<T>, allowInternal: Boolean) {
        if (!isEnabled) {
            return
        }

        if (cls.visibility == KVisibility.INTERNAL && !allowInternal) {
            throw IllegalArgumentException("binding of internal classes prohibited: $cls")
        }
    }
}