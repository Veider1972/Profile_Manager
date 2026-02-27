package ru.veider.profilemanager.utils

import android.appwidget.AppWidgetManager
import android.content.*

fun Context.updateWidget(){
    val updateIntent = Intent().apply {
        action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
    }
    sendBroadcast(updateIntent)
}