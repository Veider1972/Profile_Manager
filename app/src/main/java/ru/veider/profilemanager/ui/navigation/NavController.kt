package ru.veider.profilemanager.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.veider.profilemanager.ui.preference_activity.MainScreen
import ru.veider.profilemanager.ui.preference_activity.mode_editor.ModeEditor

@Composable
fun NavController(navController: NavHostController, startDestination: String) {
    NavHost(navController, startDestination = startDestination) {
        composable(NavDestination.MainScreen.destination) {
            MainScreen(navController = navController)
        }
        composable(NavDestination.ModeEditorScreen.destination){
            ModeEditor(navController = navController)
        }
    }
}