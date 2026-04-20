package ru.veider.profilemanager.ui.profileManager.profileEditor

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.arttttt.nav3router.Router
import org.koin.android.ext.koin.androidContext
import org.koin.compose.*
import ru.veider.profilemanager.R
import ru.veider.profilemanager.data.*
import ru.veider.profilemanager.di.*
import ru.veider.profilemanager.domain.*
import ru.veider.profilemanager.repo.Preference
import ru.veider.profilemanager.ui.assets.elements.*
import ru.veider.profilemanager.ui.assets.sliders.*
import ru.veider.profilemanager.ui.theme.*
import ru.veider.profilemanager.utils.*

@Composable
fun ProfileToolsScreen(
    router: Router<Screen>,
    profile: Profile,
    onProfileChange: (Profile) -> Unit
) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val prefs: Preference = koinInject()
//    val profiles by prefs.profiles.collectAsState()
//    val profile by rememberUpdatedState(profiles.first { it.type == profileType })
    val widget by prefs.widget.collectAsState()
    var signWidth by remember { mutableStateOf<Dp?>(null) }
    var colorWidth by remember { mutableStateOf<Dp?>(null) }

    val gradientStart by rememberUpdatedState(
        widget.background.startColor(context)
    )
    val gradientEnd by rememberUpdatedState(
        widget.background.endColor(context)
    )

    val contentWidth by rememberUpdatedState(
        signWidth?.let { signWidth ->
            colorWidth?.let { signColor ->
                listOf(signWidth, signColor).maxBy { it }
            }
        }
    )

    LaunchedEffect(Unit) {
        if (profile.guideBrightness) {
            Log.d("Brightness", "profile.brightness ${profile.brightness}")
            context.setScreenBrightness(profile.brightness)
        }
    }

    val phoneCapabilities: PhoneCapabilities = koinInject()
    val inspectionMode = LocalInspectionMode.current
    val aloud = remember { profile.type in aloudProfiles }
    val maxMediaVolume = remember { if (inspectionMode) 10f else phoneCapabilities.maxMusicVolume.toFloat() }
    val maxNotificationValue = remember { if (inspectionMode) 10f else phoneCapabilities.maxNotificationVolume.toFloat() }
    val maxAlarmVolume = remember { if (inspectionMode) 10f else phoneCapabilities.maxAlarmVolume.toFloat() }
    val maxRingValue = remember { if (inspectionMode) 10f else phoneCapabilities.maxRingVolume.toFloat() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState(0)),
        ) {
        SettingsTitle(title = stringResource(R.string.vibration))
        Item(
            title = stringResource(R.string.vibration_desc),
            onWidth = {},
            content = {
                GreenSwitch(
                    checked = profile.vibration,
                    enabled = aloud,
                    onCheckedChange = {
                        onProfileChange( profile.copy(vibration = it))
                    }
                )
            }
        )
        SettingsTitle(title = stringResource(R.string.sound))
        if (aloud){
            HorizontalDivider(color = colorPrimary)
            Item(
                title = stringResource(R.string.common_volume),
                onWidth = {},
                content = {
                    GreenSwitch(
                        checked = profile.useCommonVolume,
                        onCheckedChange = {
                            onProfileChange(
                                profile.copy(
                                    useCommonVolume = it,
                                    notificationVolume = if (it) profile.ringVolume else profile.notificationVolume,
                                    alarmVolume = if (it) profile.ringVolume else profile.alarmVolume,
                                    mediaVolume = if (it) profile.ringVolume else profile.mediaVolume,
                                )
                            )
                        }
                    )
                }
            )
        }

        HorizontalDivider(color = colorPrimary)
        SliderItem(
            icon = if (profile.useCommonVolume) R.drawable.notification_media else R.drawable.notification_ring,
            title = if (profile.useCommonVolume) stringResource(R.string.sounds_volume) else stringResource(R.string.rings_volume),
            value = profile.ringVolume.toFloat(),
            enabled = aloud,
            onChangeValue = {
                val volume = it.toInt()
                onProfileChange(
                    profile.copy(
                        ringVolume = volume,
                        notificationVolume = if (profile.useCommonVolume) volume else profile.notificationVolume,
                        alarmVolume = if (profile.useCommonVolume) volume else profile.alarmVolume,
                        mediaVolume = if (profile.useCommonVolume) volume else profile.mediaVolume,
                    ),
                )
            },
            maxValue = maxRingValue
        )
        AnimatedVisibility(
            visible = !profile.useCommonVolume || !aloud,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Column {
                HorizontalDivider(color = colorPrimary, modifier = Modifier.padding(top = singlePadding))
                SliderItem(
                    icon = R.drawable.notification_sms,
                    title = stringResource(R.string.notifications_volume),
                    value = profile.notificationVolume.toFloat(),
                    enabled = aloud,
                    onChangeValue = {
                        onProfileChange(profile.copy(notificationVolume = it.toInt()))
                    },
                    maxValue = maxNotificationValue
                )
                HorizontalDivider(color = colorPrimary, modifier = Modifier.padding(top = singlePadding))
                SliderItem(
                    icon = R.drawable.notification_alarms,
                    title = stringResource(R.string.alarm_volume),
                    value = profile.alarmVolume.toFloat(),
                    enabled = aloud,
                    onChangeValue = {
                        onProfileChange(profile.copy(alarmVolume = it.toInt()))
                    },
                    maxValue = maxAlarmVolume
                )
                HorizontalDivider(color = colorPrimary, modifier = Modifier.padding(top = singlePadding))
                SliderItem(
                    icon = R.drawable.notification_media,
                    title = stringResource(R.string.media_volume),
                    value = profile.mediaVolume.toFloat(),
                    enabled = aloud,
                    onChangeValue = {
                        onProfileChange(profile.copy(mediaVolume = it.toInt()))
                    },
                    maxValue = maxMediaVolume
                )
            }
        }


        SettingsTitle(
            title = stringResource(R.string.screen_brightness), modifier = Modifier
                .padding(top = singlePadding)
                .fillMaxWidth()
        )
        Item(
            title = stringResource(R.string.guide_brightness),
            onWidth = {},
            content = {
                Switch(
                    checked = profile.guideBrightness,
                    colors = SwitchDefaults.colors(
                        checkedTrackColor = colorPrimary
                    ),
                    onCheckedChange = {
                        onProfileChange(profile.copy(guideBrightness = it))
                    }
                )
            }
        )
        AnimatedVisibility(
            visible = profile.guideBrightness,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Column {
                HorizontalDivider(color = colorPrimary)
                Item(
                    title = stringResource(R.string.auto_brightness),
                    onWidth = {},
                    content = {
                        Switch(
                            checked = profile.brightness == null,
                            colors = SwitchDefaults.colors(
                                checkedTrackColor = colorPrimary
                            ),
                            onCheckedChange = {
                                val value = if (it) null else 20
                                onProfileChange(profile.copy(brightness = value))
                                context.setScreenBrightness(value)
                            }
                        )
                    }
                )
                HorizontalDivider(color = colorPrimary)
                AnimatedVisibility(
                    visible = profile.brightness != null,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    Column {
                        profile.brightness?.let { brightness ->
                            SliderItem(
                                icon = R.drawable.notification_brightness,
                                title = stringResource(R.string.screen_brightness),
                                value = brightness.toFloat(),
                                onChangeValue = {
                                    onProfileChange(profile.copy(brightness = it.toInt()))
                                    context.setScreenBrightness(it.toInt())
                                },
                                maxValue = 100f
                            )
                            HorizontalDivider(color = colorPrimary, modifier = Modifier.padding(top = singlePadding))
                        }
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
            }
        }

    }
}

@Composable
fun SliderItem(
    @DrawableRes icon: Int,
    title: String,
    value: Float,
    enabled: Boolean = true,
    onChangeValue: (Float) -> Unit,
    maxValue: Float
) {
    Row(
        modifier = Modifier.padding(singlePadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painterResource(icon),
            contentDescription = null
        )
        Text(
            text = title,
            style = textStyle_16_500,
            modifier = Modifier.padding(start = singlePadding)
        )
    }
    SimpleSlider(
        modifier = Modifier
            .padding(horizontal = singlePadding)
            .fillMaxWidth(),
        value = value,
        onChange = onChangeValue,
        valueRange = 0f..maxValue,
        delta = 1f,
        background = colorPrimary,
        height = 24.dp,
        enabled = enabled,
        pointerScope = { Pointer(24.dp, enabled) }
    )
}

@Composable
private fun Item(
    title: String,
    content: @Composable () -> Unit,
    contentWidth: Dp? = null,
    onWidth: ((Dp) -> Unit)? = null
) {
    val density = LocalDensity.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(singlePadding),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Text(
            text = title,
            style = textStyle_16_500,
            modifier = Modifier
                .weight(1f)
                .padding(end = singlePadding)
        )
        Box(
            modifier = Modifier
                .then(
                    if (contentWidth != null)
                        Modifier.width(contentWidth)
                    else
                        Modifier
                )
                .onPlaced { value ->
                    onWidth?.invoke(density.run { value.size.width.toDp() })
                }
        ) {
            content()
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
        Box(
            modifier = Modifier.background(colorWhite)
        ) {
            ProfileToolsScreen(
                router = router,
                profile = dayProfile(context),
                onProfileChange = {})
        }

    }

}