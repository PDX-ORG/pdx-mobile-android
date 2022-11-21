package io.github.lexadiky.pdx

import android.app.Application
import io.github.lexadiky.pdx.lib.analytics.AnalyticsModule
import io.github.lexadiky.pdx.lib.network.NetworkModule

class PdxApplication : Application() {
    val startupModules = listOf(
        AnalyticsModule,
        NetworkModule,
        ApplicationModule(this)
    )
}