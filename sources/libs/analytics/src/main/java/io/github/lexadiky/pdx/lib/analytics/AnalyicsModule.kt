package io.github.lexadiky.pdx.lib.analytics

import android.content.Context
import io.github.lexadiky.pdx.lib.analytics.sender.BloggerEventSender
import io.github.lexadiky.pdx.lib.analytics.sender.FirebaseEventSender
import io.github.lexadiky.pdx.lib.arc.di.module

val AnalyticsModule by module {
    single { createAnalyticsManager(inject()) }
}

private fun createAnalyticsManager(context: Context): AnalyticsManager {
    return AnalyticsManager().apply {
        registerEventSender(FirebaseEventSender(context))
        registerEventSender(BloggerEventSender())
    }
}