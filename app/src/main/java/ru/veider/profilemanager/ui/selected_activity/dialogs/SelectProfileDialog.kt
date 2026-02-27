package ru.veider.profilemanager.ui.selected_activity.dialogs

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
import org.koin.android.ext.koin.androidContext
import org.koin.compose.*
import ru.veider.profilemanager.di.*
import ru.veider.profilemanager.domain.Profile
import ru.veider.profilemanager.domain.preference.WidgetColor
import ru.veider.profilemanager.repo.Preference
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.*
import ru.veider.profilemanager.ui.selected_activity.assets.CombinedShape
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
    val widget by prefs.widget.collectAsState()

    val density = LocalDensity.current
    val diameterDp = 50.dp
    val diameter = remember { density.run { diameterDp.toPx() } }
    val sizeDp by remember(diameter) { mutableStateOf(diameterDp * 3) }
    val transition = rememberInfiniteTransition()
    val rotation by transition.animateFloat(
        initialValue = 0f,
        targetValue = (2*PI).toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(10000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "Transition"
    )

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
                        num = profiles.size,
                        diameter = diameter,
                        fullSize = remember(sizeDp){density.run { Size(sizeDp.toPx(), sizeDp.toPx()) }},
                        rotation = rotation
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
                    widgetBackground = widget.backgroundColor,
                    ringColor = WidgetColor.GREEN,
                    onClick = onStartPreferencesActivity
                )
            }
            if (profiles.isNotEmpty()) {
                val center by remember(sizeDp) { mutableStateOf(sizeDp / 2) }
                val radius by remember(diameterDp) { mutableStateOf(diameterDp) }
                val delta by remember(profiles.size) { mutableStateOf(2 * PI / profiles.size) }
                profiles.forEachIndexed { index, profile ->
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
                            widgetBackground = widget.backgroundColor,
                            ringColor = WidgetColor.GREEN,
                            symbol = profile.symbol,
                            symbolColor = profile.symbolColor,
                            onClick = {onAccept(profile)}
                        )
                    }
                }
            }

//            Column(
//                modifier = Modifier
//                    .fillMaxWidth(0.6f)
//                    .padding(
//                        start = dimensionResource(id = R.dimen.single_padding),
//                        bottom = dimensionResource(id = R.dimen.single_padding)
//                    )
//            ) {
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Text(
//                        stringResource(id = R.string.select_mode_title),
//                        modifier = Modifier.weight(1f, true),
//                        style = selectDialogModeFont
//                    )
//                    IconButton(onClick = {
//                        onStartPreferencesActivity()
//                    }) {
//                        Image(
//                            painter = painterResource(id = R.drawable.run_preferences),
//                            contentDescription = ""
//                        )
//                    }
//                }
//                Divider(
//                    thickness = dimensionResource(id = R.dimen.double_spacer_width),
//                    modifier = Modifier.padding(end = dimensionResource(id = R.dimen.single_padding))
//                )
//
//            profiles.forEach {
//                SelectMode(
//                    widgetBackground = widget.backgroundColor,
//                    ringColor = it.ringColor,
//                    symbol = it.symbol,
//                    symbolColor = it.symbolColor,
//                    text = it.name,
//                    timerColor = it.symbolColor.color,
//                    action = {
//                        onAccept(it)
//                    }
//                )
//            }
//            }
        }
    }
}

@Preview(widthDp = 800, heightDp = 800, apiLevel = 35)
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

