package io.github.lexadiky.pdx.lib.analytics.sender

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

internal class FirebaseEventSender(context: Context) : EventSender {

    @SuppressLint("MissingPermission")
    private val firebaseAnalytics = FirebaseAnalytics.getInstance(context)

    override fun log(event: String, parameters: Map<String, Any>) {
        firebaseAnalytics.logEvent(event, parameters.toBundle())
    }

    private fun Map<String, Any>.toBundle(): Bundle {
        val bundle = Bundle()
        entries.forEach { (key, value) ->
            when (value) {
                is String -> bundle.putString(key, value)
                is Int -> bundle.putInt(key, value)
                is Boolean -> bundle.putBoolean(key, value)
                is Double -> bundle.putDouble(key, value)
                is Float -> bundle.putFloat(key, value)
                is Byte -> bundle.putByte(key, value)
                is Short -> bundle.putShort(key, value)
                is Long -> bundle.putLong(key, value)
            }
        }

        return bundle
    }
}