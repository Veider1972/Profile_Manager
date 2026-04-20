package ru.veider.profilemanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import ru.veider.profilemanager.ui.profileManager.mainScreen.MainScreen
import ru.veider.profilemanager.ui.permissions.RequestPermissions
import ru.veider.profilemanager.ui.theme.ProfileManagerTheme
import ru.veider.profilemanager.utils.updateWidget

class EditActivity : ComponentActivity() {

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            ProfileManagerTheme{
                val navController = rememberNavController()
                RequestPermissions(
                    activity = this,
                    onGranted = {
                        MainScreen()
                    })
            }
        }
    }

    override fun onPause() {
        updateWidget()
        super.onPause()
    }
}