package ru.veider.profilemanager.ui.selected_activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import org.koin.compose.koinInject
import ru.veider.profilemanager.repo.Preference
import ru.veider.profilemanager.ui.permissions.PermissionsRequest
import ru.veider.profilemanager.ui.preference_activity.PreferenceActivity
import ru.veider.profilemanager.ui.selected_activity.dialogs.SelectProfileDialog
import ru.veider.profilemanager.ui.theme.ProfileManagerTheme
import ru.veider.profilemanager.utils.updateWidget

class SelectActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFinishOnTouchOutside(false)
        setContent {

            val prefs: Preference = koinInject()

            ProfileManagerTheme {
                PermissionsRequest(
                    activity = this,
                    onGranted = {
                        SelectProfileDialog(
                            onDismiss = {
                                finish()
                            },
                            onAccept = {
                                prefs.currentProfile.value = it.id
                                updateWidget()
                                finish()
                            },
                            onStartPreferencesActivity = {
                                val intent = Intent(this@SelectActivity, PreferenceActivity::class.java)
                                startActivity(intent)
                                finish()
                            })
                    })
            }
        }
    }
}


