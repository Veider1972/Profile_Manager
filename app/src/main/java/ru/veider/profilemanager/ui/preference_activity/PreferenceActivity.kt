package ru.veider.profilemanager.ui.preference_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.veider.profilemanager.ui.theme.ProfileManagerTheme

class PreferenceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            ProfileManagerTheme {
                TabLayout()
            }
        }
    }
}