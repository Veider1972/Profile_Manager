package ru.veider.profilemanager.ui.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.assets.enums.*
import ru.veider.profilemanager.ui.selected_activity.SelectActivity
import ru.veider.profilemanager.ui.widget.assets.*


class ProfileWidget : AppWidgetProvider() {

    override fun onEnabled(context: Context) {
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        var background: WidgetBackground
        var transparency: Int
        var ringColor: WidgetColor
        var symbol: WidgetSymbol
        var symbolColor: WidgetColor
        var name: String

        val prefs = context.getSharedPreferences(PROFILE_MANAGER, MODE_PRIVATE)

        if (prefs != null) {
            prefs.run {
                background = getInt(WIDGET_BACKGROUND, WidgetBackground.WHITE.ordinal).asWidgetBackground
                transparency = getInt(WIDGET_TRANSPARENCY, 0)
                ringColor = getInt(WIDGET_RING_COLOR, WidgetColor.ORANGE.ordinal).asWidgetColor
                symbol = getInt(WIDGET_SYMBOL, WidgetSymbol.DAY.ordinal).asWidgetSymbol
                symbolColor = getInt(WIDGET_SYMBOL_COLOR, WidgetColor.ORANGE.ordinal).asWidgetColor
                name = getString(WIDGET_NAME, "День").toString()
            }
        } else {
            background = WidgetBackground.WHITE
            transparency = 0
            ringColor = WidgetColor.ORANGE
            symbol = WidgetSymbol.DAY
            symbolColor = WidgetColor.ORANGE
            name = context.getString(R.string.mode_day_title)
        }

        RemoteViews(context.packageName, R.layout.profile_widget).apply {
            val pendingIntent = getPendingIntent(context)
            setOnClickPendingIntent(R.id.background, pendingIntent)
            for (appWidgetId in appWidgetIds) {
                setImageViewResource(R.id.background, background.resId)
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
        return PendingIntent.getActivity(context, 0, intent, 0)
    }
}