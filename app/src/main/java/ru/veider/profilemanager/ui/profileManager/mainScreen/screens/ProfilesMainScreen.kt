package ru.veider.profilemanager.ui.profileManager.mainScreen.screens

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.arttttt.nav3router.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox
import org.koin.android.ext.koin.androidContext
import org.koin.compose.*
import ru.veider.profilemanager.R
import ru.veider.profilemanager.di.*
import ru.veider.profilemanager.domain.Profile
import ru.veider.profilemanager.repo.Preference
import ru.veider.profilemanager.ui.profileManager.profileEditor.dialogs.AddProfileDialog
import ru.veider.profilemanager.ui.profileManager.mainScreen.screens.assets.PreferenceModeRow
import ru.veider.profilemanager.domain.Screen
import ru.veider.profilemanager.ui.assets.dialog.TwoButtonDialog
import ru.veider.profilemanager.ui.theme.colorGreen
import ru.veider.profilemanager.ui.theme.colorPrimary
import ru.veider.profilemanager.ui.theme.colorRed
import ru.veider.profilemanager.utils.setProfile
import ru.veider.profilemanager.utils.updateWidget

@Composable
fun ProfilesMainScreen(router: Router<Screen>) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val prefs: Preference = koinInject()
    val profiles by prefs.profiles.collectAsState()
    val widget by prefs.widget.collectAsState()
    val selectedProfilesType by prefs.selectedProfilesType.collectAsState()
    val usedProfiles by rememberUpdatedState(
        profiles.filter { it.type in selectedProfilesType }
    )
    var addProfileDialogShow by remember {mutableStateOf(false)}
    if (addProfileDialogShow){
        val availableProfiles by rememberUpdatedState(
            profiles.filter { it.type !in selectedProfilesType }
        )
        AddProfileDialog(
            profiles = availableProfiles,
            onAccept = { profile ->
                scope.launch(Dispatchers.IO){
                    addProfileDialogShow = false
                    prefs.selectedProfilesType.value = prefs.selectedProfilesType.value.toMutableList().apply {
                        this.add(profile.type)
                    }
                }

            },
            onCancel = {
                addProfileDialogShow = false
            }
        )
    }

    var deleteProfile: Profile? by remember{mutableStateOf(null)}
    var deleteProfileDialogShow by remember{mutableStateOf(false)}
    deleteProfile?.let { profile ->
        if (deleteProfileDialogShow)
            TwoButtonDialog(
                title = stringResource(R.string.attention),
                message = stringResource(R.string.ask_to_delete_profile).format(profile.type.getName(context)),
                acceptButtonText = stringResource(R.string.text_yes),
                cancelButtonText = stringResource(R.string.text_no),
                onAccept = {
                    prefs.selectedProfilesType.value = prefs.selectedProfilesType.value.filter { it != profile.type }
                    deleteProfileDialogShow = false
                },
                onCancel = {
                    deleteProfileDialogShow = false
                }
            )
    }



    Box {
        LazyColumn(
            modifier = Modifier
                .padding(vertical = dimensionResource(id = R.dimen.single_padding))
                .fillMaxSize()
        ) {
            usedProfiles.forEach { profile ->
                item {
                    val warning = SwipeAction(
                        icon = rememberVectorPainter(Icons.Default.Delete),
                        background = colorGreen,
                        onSwipe =  {}
                    )
                    val action = SwipeAction(
                        icon = rememberVectorPainter(Icons.Default.Delete),
                        background = colorRed,
                        onSwipe =  {
                            deleteProfile = profile
                            deleteProfileDialogShow = true
                        }
                    )
                    SwipeableActionsBox(
                        startActions = listOf(warning),
                        endActions = listOf(action)
                    ) {
                        PreferenceModeRow(
                            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.single_padding)),
                            widgetBackground = widget.background,
                            profile = profile,
                            runMode = {
                                prefs.currentProfileType.value = profile.type
                                profile.setProfile(context)
                                context.updateWidget()
                            },
                            editMode = {
                                router.push(Screen.ProfileEditor(profile))
                            })
                    }

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.single_padding))
                    )
                }
            }
        }
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.BottomEnd).padding(dimensionResource(R.dimen.double_padding)),
            visible = usedProfiles.size < 5,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            FloatingActionButton(
                onClick = {
                    addProfileDialogShow = true
                },
                shape = CircleShape,
                containerColor = colorPrimary,
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = dimensionResource(R.dimen.single_elevation),
                    pressedElevation = dimensionResource(R.dimen.half_elevation)
                )
            ) {
                Text(
                    text = "+",
                    fontSize = 36.sp
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    val context = LocalContext.current
    val router = remember{ Router<Screen>() }
    KoinApplication(application = {
        androidContext(context)
        modules(appModule, gsonModule)
    }) {
        ProfilesMainScreen(router)
    }
}

