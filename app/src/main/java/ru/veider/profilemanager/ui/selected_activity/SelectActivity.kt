package ru.veider.profilemanager.ui.selected_activity

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import org.koin.androidx.compose.koinViewModel
import ru.veider.profilemanager.ui.preference_activity.PreferenceActivity
import ru.veider.profilemanager.ui.selected_activity.dialogs.SelectProfileDialog
import ru.veider.profilemanager.ui.theme.ProfileManagerTheme
import ru.veider.profilemanager.viewmodel.PreferenceViewModel

class SelectActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFinishOnTouchOutside(false)
        setContent {

            val viewModel: PreferenceViewModel = koinViewModel()

            ProfileManagerTheme {
                SelectProfileDialog(onDismiss = {
                                        finish()
                                    },
                                    onAccept = {
                                        viewModel.setCurrentProfile(it)
                                        finish()
                                    },
                                    onStartPreferencesActivity = {
                                        val intent = Intent(this@SelectActivity, PreferenceActivity::class.java)
                                        startActivity(intent)
                                        finish()
                                    })
            }
        }
    }
}


