package ru.veider.profilemanager

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import org.koin.compose.koinInject
import ru.veider.profilemanager.repo.Preference
import ru.veider.profilemanager.ui.permissions.RequestPermissions
import ru.veider.profilemanager.ui.setProfile.SelectProfileDialog
import ru.veider.profilemanager.ui.theme.ProfileManagerTheme
import ru.veider.profilemanager.utils.setProfile
import ru.veider.profilemanager.utils.updateWidget

class SelectActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFinishOnTouchOutside(false)
        setContent {

            val prefs: Preference = koinInject()
            val context = LocalContext.current

            ProfileManagerTheme {
                RequestPermissions(
                    activity = this,
                    onGranted = {
                        SelectProfileDialog(
                            onDismiss = {
                                finish()
                            },
                            onAccept = {
                                prefs.currentProfileType.value = it.type
                                it.setProfile(context)
                                updateWidget()
                                finish()
                            },
                            onStartPreferencesActivity = {
                                val intent = Intent(this@SelectActivity, EditActivity::class.java)
                                startActivity(intent)
                                finish()
                            })
                    })
            }
        }
    }
}