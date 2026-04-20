package ru.veider.profilemanager.ui.profileManager.profileEditor

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arttttt.nav3router.Router
import org.koin.android.ext.koin.androidContext
import org.koin.compose.*
import ru.veider.profilemanager.R
import ru.veider.profilemanager.data.dayProfile
import ru.veider.profilemanager.di.*
import ru.veider.profilemanager.domain.*
import ru.veider.profilemanager.repo.Preference
import ru.veider.profilemanager.ui.assets.buttons.DialogButton
import ru.veider.profilemanager.ui.assets.elements.SettingsTitle
import ru.veider.profilemanager.ui.profileManager.mainScreen.screens.*
import ru.veider.profilemanager.ui.theme.singlePadding
import ru.veider.profilemanager.ui.theme.textStyle_18_500
import ru.veider.profilemanager.utils.*

@Composable
fun EditorMainState(
    router: Router<Screen>,
    profile: Profile,
) {

    val context = LocalContext.current
    val prefs: Preference = koinInject()
    val widget = remember { prefs.widget.value }
    var currentProfile by remember { mutableStateOf(profile) }
    var currentWidget by remember { mutableStateOf(widget) }

    val activeProfile = remember { prefs.profiles.value.first { it.type == prefs.currentProfileType.value } }

    DisposableEffect(Unit) {
        profile.setProfile(context)
        onDispose {
            activeProfile.setProfile(context)
        }
    }

    val tabs = listOf(
        ProfileTabItem.ProfileWidget(router, currentProfile, onProfileChange = { currentProfile = it }, currentWidget, onWidgetChange = { currentWidget = it }),
        ProfileTabItem.ProfileTools(router, currentProfile, onProfileChange = { currentProfile = it })
    )
    val pagerState = rememberPagerState(initialPage = 0, initialPageOffsetFraction = 0f, pageCount = { tabs.size })
    Scaffold(
        topBar = {
            Column{
                Tabs(tabs = tabs, pagerState = pagerState)
                SettingsTitle(title = stringResource(R.string.profile))
                Text(
                    text = profile.name,
                    style = textStyle_18_500,
                    modifier = Modifier.padding(singlePadding)
                )
            }

        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                DialogButton(
                    modifier = Modifier,
                    buttonText = stringResource(R.string.close),
                    onClick = {
                        router.push(Screen.Profiles)
                    }
                )
                DialogButton(
                    modifier = Modifier,
                    buttonText = stringResource(R.string.accept),
                    enabled = currentWidget != widget || currentProfile != profile,
                    onClick = {
                        prefs.profiles.value = prefs.profiles.value.updateBy(currentProfile)
                        prefs.widget.value = currentWidget
                        router.push(Screen.Profiles)
                    }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            TabsContent(tabs = tabs, pagerState = pagerState)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    val context = LocalContext.current
    val router = remember { Router<Screen>() }
    KoinApplication(application = {
        androidContext(context)
        modules(appModule, gsonModule)
    }) {
        EditorMainState(
            router = router,
            profile = dayProfile(context),
        )
    }

}