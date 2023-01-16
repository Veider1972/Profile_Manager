package ru.veider.profilemanager.ui.preference_activity

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import ru.veider.profilemanager.ui.theme.ProfileManagerTheme

@Composable
fun MainScreen(navController: NavController){
    ProfileManagerTheme {
        MainTabLayout(navController)
    }
}