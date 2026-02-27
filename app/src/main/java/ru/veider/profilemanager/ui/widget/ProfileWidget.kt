package ru.veider.profilemanager.ui.widget

import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.appwidget.*
import android.content.*
import android.util.Log
import android.widget.RemoteViews
import org.koin.java.KoinJavaComponent.inject
import ru.veider.profilemanager.R
import ru.veider.profilemanager.repo.Preference
import ru.veider.profilemanager.ui.selected_activity.SelectActivity
import ru.veider.profilemanager.utils.gradientCircleBitmap


class ProfileWidget : AppWidgetProvider() {

    override fun onEnabled(context: Context) {
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        val prefs: Preference by inject(Preference::class.java)
        val background = prefs.widget.value.backgroundColor
        val transparency = prefs.widget.value.backgroundTransparency.value
        val profile = prefs.profiles.value.first { it.id ==  prefs.currentProfile.value}
        val ringColor = profile.ringColor
        val symbol = profile.symbol
        val symbolColor = profile.symbolColor
        val name = profile.name

        RemoteViews(context.packageName, R.layout.profile_widget).apply {
            val pendingIntent = getPendingIntent(context)
            setOnClickPendingIntent(R.id.background, pendingIntent)
            for (appWidgetId in appWidgetIds) {
                val bitmap = gradientCircleBitmap(100, background.gradient(context).first(), background.gradient(context).last())
                setImageViewBitmap(R.id.background, bitmap)
//                setImageViewResource(R.id.background, background.resId)
                setInt(R.id.background,"setAlpha", (transparency*2.55).toInt())
                setImageViewResource(R.id.ring, ringColor.ringId)
                setImageViewResource(R.id.symbol, symbol.imageId(symbolColor))
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
        return PendingIntent.getActivity(context, 0, intent, FLAG_IMMUTABLE)
    }
}