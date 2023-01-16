package ru.veider.profilemanager.ui.selected_activity

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.koin.androidx.compose.koinViewModel
import ru.veider.profilemanager.ui.preference_activity.PreferenceActivity
import ru.veider.profilemanager.ui.selected_activity.dialogs.SelectProfileDialog
import ru.veider.profilemanager.ui.theme.ProfileManagerTheme
import ru.veider.profilemanager.ui.widget.assets.WIDGET_MODE
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
                                        val updateIntent = Intent().apply {
                                            action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
                                            putExtra(WIDGET_MODE, it.name)
                                        }
                                        sendBroadcast(updateIntent)
                                        viewModel.setProfile(it)
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


