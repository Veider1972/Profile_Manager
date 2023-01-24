package ru.veider.profilemanager.ui.preference_activity.assets.enums

import ru.veider.profilemanager.R

enum class WidgetSymbol {
    DAY, NIGHT, HOME, OFFICE, CAR, SILENT, ALOUD;
    val imageId
        get() = when (this) {
            DAY    -> R.drawable.widget_symbol_day
            NIGHT  -> R.drawable.widget_symbol_night
            HOME   -> R.drawable.widget_symbol_home
            OFFICE -> R.drawable.widget_symbol_office
            CAR    -> R.drawable.widget_symbol_car
            SILENT -> R.drawable.widget_symbol_silent
            ALOUD  -> R.drawable.widget_symbol_aloud
        }
}

val Int.asWidgetSymbol
    get() =
        when (this) {
            WidgetSymbol.DAY.ordinal    -> WidgetSymbol.DAY
            WidgetSymbol.NIGHT.ordinal  -> WidgetSymbol.NIGHT
            WidgetSymbol.HOME.ordinal   -> WidgetSymbol.HOME
            WidgetSymbol.OFFICE.ordinal -> WidgetSymbol.OFFICE
            WidgetSymbol.CAR.ordinal    -> WidgetSymbol.CAR
            WidgetSymbol.SILENT.ordinal -> WidgetSymbol.SILENT
            else                        -> WidgetSymbol.ALOUD
        }