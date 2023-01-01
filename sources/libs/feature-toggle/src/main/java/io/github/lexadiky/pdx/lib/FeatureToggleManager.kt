@file:OptIn(ExperimentalTime::class)

package io.github.lexadiky.pdx.lib

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import io.github.lexadiky.pdx.lib.blogger.BLogger
import io.github.lexadiky.pdx.lib.blogger.error
import io.github.lexadiky.pdx.lib.blogger.info
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeout
import kotlin.coroutines.resume
import kotlin.time.ExperimentalTime
import kotlin.time.seconds

class FeatureToggleManager {

    private val firebaseRemoteConfig: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

    init {
        val settings = FirebaseRemoteConfigSettings.Builder()
            // TODO .setMinimumFetchIntervalInSeconds(if (BuildConfig.DEBUG) 0 else 3600)
            .build()
        firebaseRemoteConfig.setConfigSettingsAsync(settings)
            .addOnFailureListener { throwable ->
                BLogger.tag("FeatureToggleManager")
                    .error("can't set firebase settings", throwable)
            }
    }

    suspend fun sync() {
        withTimeout(SYNC_TIMEOUT) {
            syncInternal()
            BLogger.tag("FeatureToggleManager").info("successfully synced firebase remote config")
        }
    }

    fun resolve(toggle: BooleanFeatureToggle): Boolean {
        val configKey = formatFeatureToggleFbKey(toggle)
        return try {
            firebaseRemoteConfig.all.getOrDefault(configKey, null)
                ?.asBoolean()
                ?: toggle.defaultValue
        } catch (e: Throwable) {
            BLogger.tag("FeatureToggleManager")
                .error("can't resolve toggle $configKey", e)
            toggle.defaultValue
        }
    }

    private fun formatFeatureToggleFbKey(toggle: FeatureToggle<*>): String {
        return "ft_${toggle.group}_${toggle.id}"
    }

    private suspend fun syncInternal() = suspendCancellableCoroutine {  cont ->
        firebaseRemoteConfig.fetchAndActivate()
            .addOnCanceledListener {
                cont.cancel()
            }
            .addOnFailureListener { throwable ->
                BLogger.tag("FeatureToggleManager")
                    .error("failed to fetch firebase remote config", throwable)
                cont.resume(Unit)
            }
            .addOnSuccessListener {
                cont.resume(Unit)
            }
    }

    companion object {

        private val SYNC_TIMEOUT = 10.seconds
    }
}

inline fun FeatureToggleManager.ifEnabled(toggle: BooleanFeatureToggle, callback: () -> Unit) {
    if (resolve(toggle)) {
        callback()
    }
}
