package ru.veider.profilemanager.ui.preference_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import ru.veider.profilemanager.ui.navigation.NavController
import ru.veider.profilemanager.ui.permissions.PermissionsRequest

class PreferenceActivity : ComponentActivity() {

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            PermissionsRequest(
                activity = this,
                onGranted = {
                    NavController(navController = navController, startDestination = "mainScreen")
                })
        }
    }

    override fun onPause() {
        super.onPause()
    }
}