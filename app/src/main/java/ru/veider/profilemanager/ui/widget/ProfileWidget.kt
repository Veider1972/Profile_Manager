package ru.veider.profilemanager.ui.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.selected_activity.SelectActivity
import ru.veider.profilemanager.ui.widget.assets.WIDGET_MODE
import ru.veider.profilemanager.ui.widget.assets.WidgetMode


class ProfileWidget : AppWidgetProvider() {

    val TAG = "TAG"

    var mode = WidgetMode.DAY


    override fun onEnabled(context: Context) {
        onReceive(context, Intent().apply {
            action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
            putExtra(WIDGET_MODE, WidgetMode.DAY.name)
        })
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them

        when (mode){
            WidgetMode.DAY   ->{
                RemoteViews(context.packageName, R.layout.profile_widget_day).apply {
                    val pendingIntent = getPendingIntent(context)
                    setOnClickPendingIntent(R.id.background, pendingIntent)
                    for (appWidgetId in appWidgetIds) {
                        appWidgetManager.updateAppWidget(appWidgetId, this)
                    }
                }
            }
            WidgetMode.NIGHT   ->{
                RemoteViews(context.packageName, R.layout.profile_widget_night).apply {
                    val pendingIntent = getPendingIntent(context)
                    setOnClickPendingIntent(R.id.background, pendingIntent)
                    for (appWidgetId in appWidgetIds) {
                        appWidgetManager.updateAppWidget(appWidgetId, this)
                    }
                }
            }
        }
    }

    override fun onReceive(context: Context, intent: Intent?) {
        intent?.let {
            val widgetManager = AppWidgetManager.getInstance(context)
            val widgetIds = widgetManager.getAppWidgetIds(ComponentName(context, ProfileWidget::class.java))
            if (it.hasExtra(WIDGET_MODE)) {
                mode = WidgetMode.valueOf(it.extras!!.getString(WIDGET_MODE)!!)
                when (mode){
                    WidgetMode.DAY   ->{
                        RemoteViews(context.packageName, R.layout.profile_widget_day).apply {
                            val pendingIntent = getPendingIntent(context)
                            setOnClickPendingIntent(R.id.background, pendingIntent)
                            for (appWidgetId in widgetIds) {
                                widgetManager.updateAppWidget(appWidgetId, this)
                            }
                        }
                    }
                    WidgetMode.NIGHT   ->{
                        RemoteViews(context.packageName, R.layout.profile_widget_night).apply {
                            val pendingIntent = getPendingIntent(context)
                            setOnClickPendingIntent(R.id.background, pendingIntent)
                            for (appWidgetId in widgetIds) {
                                widgetManager.updateAppWidget(appWidgetId, this)
                            }
                        }
                    }
                }
            }
        }
        super.onReceive(context, intent)
    }

    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
        super.onDeleted(context, appWidgetIds)
        Log.d(TAG, "onDeleted $appWidgetIds")
    }

    override fun onDisabled(context: Context?) {
        super.onDisabled(context)
        Log.d(TAG, "onDisabled")
    }

    private fun getPendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, SelectActivity::class.java)
        return PendingIntent.getActivity(context, 0, intent, 0)
    }
}