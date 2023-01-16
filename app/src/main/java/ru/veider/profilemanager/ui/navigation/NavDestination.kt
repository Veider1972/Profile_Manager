package ru.veider.profilemanager.ui.navigation

sealed class NavDestination(val destination: String){
    object MainScreen: NavDestination("mainScreen")
    object ModeEditorScreen: NavDestination("modeEditorScreen")
}
