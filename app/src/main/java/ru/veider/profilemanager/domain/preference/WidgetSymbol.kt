package ru.veider.profilemanager.domain.preference

import ru.veider.profilemanager.R

enum class WidgetSymbol {
    DAY, NIGHT, HOME, OFFICE, CAR, SILENT, ALOUD;
    fun imageId(color: WidgetColor)
        = when (this) {
            DAY    -> {when (color){
                WidgetColor.BLUE   -> R.drawable.widget_symbol_day_blue
                WidgetColor.ORANGE -> R.drawable.widget_symbol_day_orange
                WidgetColor.PURPLE -> R.drawable.widget_symbol_day_purple
                WidgetColor.RED    -> R.drawable.widget_symbol_day_red
                WidgetColor.YELLOW -> R.drawable.widget_symbol_day_yellow
                WidgetColor.GREEN  -> R.drawable.widget_symbol_day_green
            }}
            NIGHT  -> {when (color){
                WidgetColor.BLUE   -> R.drawable.widget_symbol_night_blue
                WidgetColor.ORANGE -> R.drawable.widget_symbol_night_orange
                WidgetColor.PURPLE -> R.drawable.widget_symbol_night_purple
                WidgetColor.RED    -> R.drawable.widget_symbol_night_red
                WidgetColor.YELLOW -> R.drawable.widget_symbol_night_yellow
                WidgetColor.GREEN  -> R.drawable.widget_symbol_night_green
            }}
            HOME   -> {when (color){
                WidgetColor.BLUE   -> R.drawable.widget_symbol_home_blue
                WidgetColor.ORANGE -> R.drawable.widget_symbol_home_orange
                WidgetColor.PURPLE -> R.drawable.widget_symbol_home_purple
                WidgetColor.RED    -> R.drawable.widget_symbol_home_red
                WidgetColor.YELLOW -> R.drawable.widget_symbol_home_yellow
                WidgetColor.GREEN  -> R.drawable.widget_symbol_home_green
            }}
            OFFICE -> {when (color){
                WidgetColor.BLUE   -> R.drawable.widget_symbol_office_blue
                WidgetColor.ORANGE -> R.drawable.widget_symbol_office_orange
                WidgetColor.PURPLE -> R.drawable.widget_symbol_office_purple
                WidgetColor.RED    -> R.drawable.widget_symbol_office_red
                WidgetColor.YELLOW -> R.drawable.widget_symbol_office_yellow
                WidgetColor.GREEN  -> R.drawable.widget_symbol_office_green
            }}
            CAR    -> {when (color){
                WidgetColor.BLUE   -> R.drawable.widget_symbol_car_blue
                WidgetColor.ORANGE -> R.drawable.widget_symbol_car_orange
                WidgetColor.PURPLE -> R.drawable.widget_symbol_car_purple
                WidgetColor.RED    -> R.drawable.widget_symbol_car_red
                WidgetColor.YELLOW -> R.drawable.widget_symbol_car_yellow
                WidgetColor.GREEN  -> R.drawable.widget_symbol_car_green
            }}
            SILENT -> {when (color){
                WidgetColor.BLUE   -> R.drawable.widget_symbol_silent_blue
                WidgetColor.ORANGE -> R.drawable.widget_symbol_silent_orange
                WidgetColor.PURPLE -> R.drawable.widget_symbol_silent_purple
                WidgetColor.RED    -> R.drawable.widget_symbol_silent_red
                WidgetColor.YELLOW -> R.drawable.widget_symbol_silent_yellow
                WidgetColor.GREEN  -> R.drawable.widget_symbol_silent_green
            }}
            ALOUD  -> {when (color){
                WidgetColor.BLUE   -> R.drawable.widget_symbol_aloud_blue
                WidgetColor.ORANGE -> R.drawable.widget_symbol_aloud_orange
                WidgetColor.PURPLE -> R.drawable.widget_symbol_aloud_purple
                WidgetColor.RED    -> R.drawable.widget_symbol_aloud_red
                WidgetColor.YELLOW -> R.drawable.widget_symbol_aloud_yellow
                WidgetColor.GREEN  -> R.drawable.widget_symbol_aloud_green
            }}
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