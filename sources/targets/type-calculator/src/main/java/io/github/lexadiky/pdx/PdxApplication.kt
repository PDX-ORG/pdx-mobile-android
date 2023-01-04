package io.github.lexadiky.pdx

import android.app.Application
import com.google.firebase.crashlytics.FirebaseCrashlytics
import io.github.lexadiky.pdx.domain.achievement.AchievementModule
import io.github.lexadiky.pdx.lib.FeatureToggleManager
import io.github.lexadiky.pdx.lib.FeatureToggleModule
import io.github.lexadiky.pdx.lib.analytics.AnalyticsModule
import io.github.lexadiky.pdx.lib.arc.di.DIContainer
import io.github.lexadiky.pdx.lib.network.NetworkModule
import io.github.lexadiky.pdx.ui.uikit.UikitModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class PdxApplication : Application() {
    val diContainer = DIContainer(
        AnalyticsModule,
        NetworkModule,
        FeatureToggleModule,
        UikitModule,
        AchievementModule,
        ApplicationModule(this)
    )

    override fun onCreate() {
        super.onCreate()

        ApplicationInitializer()
            .task("sync_feature_toggles") { diContainer.lookup<FeatureToggleManager>().sync() }
            .run()
    }
}