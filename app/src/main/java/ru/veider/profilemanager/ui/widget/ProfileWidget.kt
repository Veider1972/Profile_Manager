package ru.veider.profilemanager.ui.widget

import android.app.PendingIntent
import android.appwidget.*
import android.content.*
import android.util.Log
import android.widget.RemoteViews
import androidx.compose.ui.graphics.toArgb
import org.koin.java.KoinJavaComponent.inject
import ru.veider.profilemanager.R
import ru.veider.profilemanager.data.dayProfile
import ru.veider.profilemanager.repo.Preference
import ru.veider.profilemanager.SelectActivity
import ru.veider.profilemanager.utils.*


class ProfileWidget : AppWidgetProvider() {

    override fun onEnabled(context: Context) {
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        val prefs: Preference by inject(Preference::class.java)
        val widgetBackground = prefs.widget.value.background
        val currentProfileType = prefs.currentProfileType.value
        val currentProfile = prefs.profiles.value.firstOrNull { it.type == currentProfileType } ?: dayProfile(context)
        val color = currentProfile.color
        val symbol = currentProfile.symbol
        val name = currentProfile.name

        RemoteViews(context.packageName, R.layout.profile_widget).apply {
            val pendingIntent = getPendingIntent(context)
            setOnClickPendingIntent(R.id.background, pendingIntent)
            for (appWidgetId in appWidgetIds) {
                val gradient = gradientCircleBitmap(100, widgetBackground.startColor(context), widgetBackground.endColor(context))
                setImageViewBitmap(R.id.background, gradient)
//                setInt(R.id.background,"setAlpha", (widgetBackground.transparency*255).toInt())
                val symbol = drawVector(context, symbol.symbol, color.toArgb())
                setImageViewBitmap(R.id.symbol, symbol)
                val ring = drawVector(context, R.drawable.ring, color.toArgb())
                setImageViewBitmap(R.id.ring, ring)
                appWidgetManager.updateAppWidget(appWidgetId, this)
            }
        }
        Log.d("TAG", "onUpdate:")
    }

    override fun onReceive(context: Context, intent: Intent) {
        val widgetManager = AppWidgetManager.getInstance(context)
        val widgetIds = widgetManager.getAppWidgetIds(ComponentName(context, ProfileWidget::class.java))
        onUpdate(context, widgetManager, widgetIds)
        super.onReceive(context, intent)
    }

    private fun getPendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, SelectActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
    }
}