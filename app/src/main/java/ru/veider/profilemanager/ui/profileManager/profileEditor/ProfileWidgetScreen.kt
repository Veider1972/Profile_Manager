package ru.veider.profilemanager.ui.profileManager.profileEditor

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.arttttt.nav3router.Router
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.compose.KoinApplication
import ru.veider.profilemanager.R
import ru.veider.profilemanager.data.dayProfile
import ru.veider.profilemanager.di.*
import ru.veider.profilemanager.domain.*
import ru.veider.profilemanager.ui.assets.elements.SettingsTitle
import ru.veider.profilemanager.ui.profileManager.assets.dialogs.assets.DrawWidget
import ru.veider.profilemanager.ui.profileManager.profileEditor.dialogs.*
import ru.veider.profilemanager.ui.theme.*

@Composable
fun ProfileWidgetScreen(
    router: Router<Screen>,
    profile: Profile,
    onProfileChange: (Profile) -> Unit,
    widget: Widget,
    onWidgetChange: (Widget) -> Unit
) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
//    val prefs: Preference = koinInject()
//    val profiles by prefs.profiles.collectAsState()
//    val profile by rememberUpdatedState(profiles.first { it.type == profileType })
//    val widget by prefs.widget.collectAsState()
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

    var widgetColorDialogShow by remember { mutableStateOf(false) }
    if (widgetColorDialogShow)
        SetColorDialog(
            profile = profile,
            widget = widget,
            onAccept = {
                scope.launch {
                    widgetColorDialogShow = false
                    onProfileChange(it)
                }
            },
            onCancel = {
                widgetColorDialogShow = false
            }
        )

    var widgetGradientDialogShow by remember { mutableStateOf(false) }
    if (widgetGradientDialogShow)
        SetGradientDialog(
            profile = profile,
            widget = widget,
            onAccept = {
                scope.launch {
                    widgetGradientDialogShow = false
                    onWidgetChange(it)
                }
            },
            onCancel = {
                widgetGradientDialogShow = false
            },
        )


    Column(modifier = Modifier.fillMaxWidth()) {

        SettingsTitle(
            title = stringResource(R.string.widget_visual_style)
        )

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.transparent_background),
                contentDescription = null,
                modifier = Modifier
                    .size(LocalMinimumInteractiveComponentSize.current * 1.5f)
                    .clip(CircleShape)
            )
            DrawWidget(
                modifier = Modifier.padding(singlePadding),
                size = LocalMinimumInteractiveComponentSize.current * 1.5f,
                widgetBackground = widget.background,
                color = profile.color,
                symbol = profile.symbol
            )
        }
        SettingsTitle(title = stringResource(R.string.widget_individual_visual_style))
        Item(
            title = stringResource(R.string.widget_color),
            contentWidth = contentWidth,
            onWidth = {
                if (colorWidth == null)
                    colorWidth = it
            },
            content = {
                Box(
                    modifier = Modifier
                        .size(LocalMinimumInteractiveComponentSize.current + 2.dp)
                        .clip(RoundedCornerShape(singleCorner))
                        .background(color = profile.color)
                        .border(
                            width = 1.dp,
                            color = colorPrimary,
                            shape = RoundedCornerShape(singleCorner)
                        )
                        .clickable {
                            widgetColorDialogShow = true
                        }
                )
            }
        )
        SettingsTitle(title = stringResource(R.string.widget_common_visual_style))
        Text(
            modifier = Modifier
                .padding(horizontal = singlePadding)
                .align(Alignment.End),
            text = stringResource(R.string.widget_common_visual_style_comment),
            color = colorGray
        )
        Item(
            title = stringResource(R.string.widget_background),
            contentWidth = contentWidth,
            onWidth = {
                if (colorWidth == null)
                    colorWidth = it
            },
            content = {
                Box(
                    modifier = Modifier
                        .size(LocalMinimumInteractiveComponentSize.current + 2.dp)
                        .clip(RoundedCornerShape(singleCorner))
                        .background(brush = Brush.linearGradient(listOf(gradientStart, gradientEnd)))
                        .border(
                            width = 1.dp,
                            color = colorPrimary,
                            shape = RoundedCornerShape(singleCorner)
                        )
                        .clickable {
                            widgetGradientDialogShow = true
                        }
                )
            }
        )
        HorizontalDivider(color = colorPrimary)
        Spacer(modifier = Modifier.weight(1f))
    }

}

@Composable
private fun Item(
    title: String,
    content: @Composable () -> Unit,
    contentWidth: Dp? = null,
    onWidth: (Dp) -> Unit
) {
    val density = LocalDensity.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(singlePadding),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Text(
            text = title,
            style = textStyle_16_500
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
                    onWidth(density.run { value.size.width.toDp() })
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
        ProfileWidgetScreen(
            router = router,
            profile = dayProfile(context),
            onProfileChange = {},
            widget = Widget(),
            onWidgetChange = {}
        )
    }

}