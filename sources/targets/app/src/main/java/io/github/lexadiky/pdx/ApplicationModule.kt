package io.github.lexadiky.pdx

import android.content.Context
import io.github.lexadiky.pdx.lib.arc.di.eagerModule

fun ApplicationModule(context: Context) = eagerModule {
    single { context }
}