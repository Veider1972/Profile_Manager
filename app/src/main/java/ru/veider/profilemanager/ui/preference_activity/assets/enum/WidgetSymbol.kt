package ru.veider.profilemanager.ui.preference_activity.assets.enum

import ru.veider.profilemanager.R

enum class WidgetSymbol(val value: Int) {
    DAY(0), NIGHT(1), HOME(2), OFFICE(3), CAR(4), SILENT(5), ALOUD(6);

    val imageId: Int
        get() = when (this) {
            DAY -> R.drawable.widget_symbol_day
            NIGHT -> R.drawable.widget_symbol_night
            HOME -> R.drawable.widget_symbol_home
            OFFICE -> R.drawable.widget_symbol_office
            CAR -> R.drawable.widget_symbol_car
            SILENT -> R.drawable.widget_symbol_silent
            ALOUD -> R.drawable.widget_symbol_aloud
        }
}