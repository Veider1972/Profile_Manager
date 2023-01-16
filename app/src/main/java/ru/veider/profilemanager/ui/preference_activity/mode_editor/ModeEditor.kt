package ru.veider.profilemanager.ui.preference_activity.mode_editor

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import ru.veider.profilemanager.ui.theme.ProfileManagerTheme

@Composable
fun ModeEditor(navController: NavController){
    ProfileManagerTheme {
        ModeEditorTabLayout(navController)
    }
}