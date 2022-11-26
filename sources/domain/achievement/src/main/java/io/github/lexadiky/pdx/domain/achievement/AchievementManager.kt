package io.github.lexadiky.pdx.domain.achievement

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.content.Context
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import io.github.lexadiky.pdx.domain.achievement.library.Achievement
import io.github.lexadiky.pdx.lib.blogger.BLogger
import io.github.lexadiky.pdx.lib.blogger.error
import io.github.lexadiky.pdx.lib.blogger.verbose
import io.github.lexadiky.pdx.ui.uikit.resources.render
import kotlin.random.Random

class AchievementManager(
    private val context: Context,
    private val achievementLibraryFactory: AchievementLibraryFactory
) {

    private val sharedPreferences = context.getSharedPreferences(SP_SPACE, Context.MODE_PRIVATE)
    private val notificationManager = NotificationManagerCompat.from(context)

    fun give(achievement: Achievement) {
        if (sharedPreferences.contains(SP_NAME_PREFIX + achievement.id)) {
            BLogger.tag("AchievementManager")
                .verbose("achievement '${achievement.id}' skipped due to repeat")
            return
        }

        BLogger.tag("AchievementManager")
            .verbose("achievement '${achievement.id}' given")
        sharedPreferences.edit()
            .putBoolean(SP_NAME_PREFIX + achievement.id, true)
            .apply()
        showAchievement(achievement)
    }

    fun drop() {
        sharedPreferences.edit()
            .clear()
            .apply()
    }

    @SuppressLint("MissingPermission")
    private fun showAchievement(achievement: Achievement) {
        try {
            notificationManager.createNotificationChannel(
                NotificationChannelCompat.Builder(NOTIFICATION_CHANNEL, NotificationManagerCompat.IMPORTANCE_HIGH)
                    .setDescription(context.getString(R.string.achievement_notification_channel_description))
                    .setName(context.getString(R.string.achievement_notification_channel_name))
                    .build()
            )

            val notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL)
                .setSmallIcon(androidx.core.R.drawable.notification_bg)
                .setContentTitle(achievement.name.render(context))
                .setContentText(achievement.description.render(context))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()

            notificationManager.notify(achievement.id.hashCode(), notification)
        } catch (e: Throwable) {
            BLogger.tag("AchievementManager")
                .error("can't show notification", e)
        }
    }

    suspend fun listGivenAchievements(): List<Achievement> {
        return achievementLibraryFactory.list()
            .filter { sharedPreferences.contains(SP_NAME_PREFIX + it.id) }
    }

    companion object {

        private const val SP_SPACE = "achievement"
        private const val SP_NAME_PREFIX = "ac@"

        private const val NOTIFICATION_CHANNEL = "achievements"
    }
}