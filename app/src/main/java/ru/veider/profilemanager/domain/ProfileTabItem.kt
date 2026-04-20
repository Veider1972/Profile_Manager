package ru.veider.profilemanager.domain

import androidx.compose.runtime.Composable
import com.arttttt.nav3router.Router
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.profileManager.profileEditor.ProfileToolsScreen
import ru.veider.profilemanager.ui.profileManager.profileEditor.ProfileWidgetScreen

sealed class ProfileTabItem(override val title: Int, override val screen: @Composable (() -> Unit)): Tab {
    data class ProfileTools(val router: Router<Screen>, val profile: Profile, val onProfileChange: (Profile) -> Unit)
        : ProfileTabItem(R.string.profile_tools, { ProfileToolsScreen(router, profile, onProfileChange) })
    data class ProfileWidget(val router: Router<Screen>, val profile: Profile, val onProfileChange: (Profile) -> Unit, val widget: Widget, val onWidgetChange: (Widget) -> Unit)
        : ProfileTabItem(R.string.profile_widget, { ProfileWidgetScreen(router, profile, onProfileChange , widget, onWidgetChange) })
}