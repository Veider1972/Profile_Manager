package ru.veider.profilemanager.ui.preference_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import ru.veider.profilemanager.ui.navigation.NavController

class PreferenceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            NavController(navController = navController, startDestination = "mainScreen")

        }
    }
}