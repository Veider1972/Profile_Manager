package ru.veider.profilemanager.ui.setProfile

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import org.koin.android.ext.koin.androidContext
import org.koin.compose.*
import ru.veider.profilemanager.di.*
import ru.veider.profilemanager.domain.Profile
import ru.veider.profilemanager.domain.WidgetBackground
import ru.veider.profilemanager.repo.Preference
import ru.veider.profilemanager.ui.profileManager.assets.dialogs.assets.*
import ru.veider.profilemanager.ui.setProfile.assets.CombinedShape
import ru.veider.profilemanager.ui.theme.colorGreen
import ru.veider.profilemanager.ui.theme.colorPrimary
import kotlin.math.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SelectProfileDialog(
    onDismiss: () -> Unit,
    onAccept: (Profile) -> Unit,
    onStartPreferencesActivity: () -> Unit
) {

    val prefs: Preference = koinInject()
    val profiles by prefs.profiles.collectAsState()
    val currentProfiles by prefs.selectedProfilesType.collectAsState()
    val usedProfiles by rememberUpdatedState(
        profiles.filter { it.type in currentProfiles }
    )
    val widget by prefs.widget.collectAsState()

    val density = LocalDensity.current
    val diameterDp = 75.dp
    val diameter = remember { density.run { diameterDp.toPx() } }
    val sizeDp by remember(diameter) { mutableStateOf(diameterDp * 3) }
    var rotation by remember { mutableStateOf(0.0) }
    val timeLength = 60000L
    val steps = 2880
    val deltaAngle = remember { 2* PI / steps }
    val deltaTime = remember{timeLength / steps}

    LaunchedEffect(Unit) {
        var currentStep = 0
        while(isActive){
            rotation = deltaAngle * currentStep++
            if (currentStep == steps)
                currentStep = 0
            delay(deltaTime)
        }
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnClickOutside = true,
            dismissOnBackPress = true,
            securePolicy = SecureFlagPolicy.SecureOff,
            decorFitsSystemWindows = false,
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = Modifier

                .size(sizeDp)
                .clip(
                    CombinedShape(
                        num = usedProfiles.size,
                        diameter = diameter,
                        fullSize = remember(sizeDp){density.run { Size(sizeDp.toPx(), sizeDp.toPx()) }},
                        rotation = rotation.toFloat()
                    )
                )
                .background(Color.White)

        ) {

            Box(
                modifier = Modifier
                    .padding(
                        start = sizeDp / 2 - diameterDp / 3,
                        top = sizeDp / 2 - diameterDp / 3
                    )

            ) {
                DrawMenu(
                    size = diameterDp * 2 / 3,
                    widgetBackground = WidgetBackground.White,
                    color = colorGreen,
                    onClick = onStartPreferencesActivity
                )
            }
            if (usedProfiles.isNotEmpty()) {
                val center by remember(sizeDp) { mutableStateOf(sizeDp / 2) }
                val radius by remember(diameterDp) { mutableStateOf(diameterDp) }
                val delta by remember(usedProfiles.size) { mutableDoubleStateOf(2 * PI / usedProfiles.size) }
                usedProfiles.forEachIndexed { index, profile ->
                    val x by rememberUpdatedState(center + radius * sin(index * delta + rotation).toFloat() - (diameterDp * 0.5f))
                    val y by rememberUpdatedState(center - radius * cos(index * delta + rotation).toFloat() - (diameterDp * 0.5f))
                    Box(
                        modifier = Modifier
                            .padding(
                                start = x,
                                top = y
                            )
                    ) {
                        DrawWidget(
                            size = diameterDp,
                            widgetBackground = widget.background,
                            color = profile.color,
                            symbol = profile.symbol,
                            onClick = {onAccept(profile)}
                        )
                    }
                }
            }
        }
    }
}

@Preview(widthDp = 300, heightDp = 300, apiLevel = 35)
@Composable
private fun Preview() {
    val context = LocalContext.current
    KoinApplication(application = {
        androidContext(context)
        modules(appModule, gsonModule)
    }) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            SelectProfileDialog(
                onDismiss = { },
                onAccept = {},
                onStartPreferencesActivity = {}
            )
        }
    }
}

