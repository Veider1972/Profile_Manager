package ru.veider.profilemanager.ui.profileManager.profileEditor.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.github.skydoves.colorpicker.compose.*
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.compose.KoinApplication
import ru.veider.profilemanager.R
import ru.veider.profilemanager.data.dayProfile
import ru.veider.profilemanager.di.*
import ru.veider.profilemanager.domain.*
import ru.veider.profilemanager.ui.assets.buttons.*
import ru.veider.profilemanager.ui.assets.dialog.DialogWrapper
import ru.veider.profilemanager.ui.assets.elements.DialogTitle
import ru.veider.profilemanager.ui.assets.sliders.*
import ru.veider.profilemanager.ui.profileManager.assets.dialogs.assets.DrawWidget
import ru.veider.profilemanager.ui.theme.singlePadding
import ru.veider.profilemanager.utils.*

@Composable
fun SetColorDialog(
    profile: Profile,
    widget: Widget,
    onAccept: (Profile) -> Unit,
    onCancel: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val controller = rememberColorPickerController()
    var currentColor by remember { mutableStateOf(profile.color) }
    val elementHeight = 24.dp
    LaunchedEffect(key1 = Unit) {
        controller.wheelRadius = 10.dp
    }

    DialogWrapper() {
        Column(
            modifier = Modifier.width(IntrinsicSize.Max)
        ) {
            DialogTitle(title = stringResource(id = R.string.widget_color))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(singlePadding),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painterResource(R.drawable.transparent_background),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(LocalMinimumInteractiveComponentSize.current * 1.5f)
                )
                DrawWidget(
                    size = LocalMinimumInteractiveComponentSize.current * 1.5f,
                    widgetBackground = widget.background,
                    color = currentColor,
                    symbol = profile.symbol
                )
                val chipColor by rememberUpdatedState(colorResource(R.color.widget_default))
                Chip(
                    imageId = R.drawable.restore,
                    selected = false,
                    onClick = {
                        scope.launch {
                            currentColor = chipColor
                        }

                    },
                    modifier = Modifier.align(Alignment.TopEnd)
                )

            }
            HsvColorPicker(
                initialColor = profile.color,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = singlePadding)
                    .aspectRatio(1f),
                controller = controller,
                onColorChanged = { colorEnvelope: ColorEnvelope ->
                    val cmyk = colorEnvelope.color.toCmyk()
                    currentColor = currentColor.toCmyk().copy(cyan = cmyk.cyan, magenta = cmyk.magenta, yellow = cmyk.yellow).toColor()
                    controller.wheelColor = currentColor.contrast()
                }
            )
            TransparencySlider(
                modifier = Modifier.padding(start = singlePadding, top = singlePadding, end = singlePadding),
                value = 1f - currentColor.toCmyk().black,
                onChange = {
                    currentColor = currentColor.toCmyk().copy(black = 1f - it).toColor()
                    controller.selectByColor(currentColor, true)
                },
                valueRange = 0f..1f,
                delta = 0.01f,
                background = Brush.linearGradient(
                    listOf(
                        controller.selectedColor.value.toCmyk().copy(black = 1f, alpha = 1f).toColor(),
                        controller.selectedColor.value.toCmyk().copy(black = 0f, alpha = 1f).toColor()
                    )
                ),
                height = elementHeight,
                pointerScope = { Pointer(elementHeight) }
            )
            DialogButtons(
                modifier = Modifier.fillMaxWidth(),
                acceptEnabled = currentColor != profile.color,
                onAccept = {
                    onAccept(
                        profile.copy(color = currentColor)
                    )
                },
                onCancel = onCancel
            )
        }

    }
}

@Preview
@Composable
private fun Preview() {
    val context = LocalContext.current
    val navController = rememberNavController()
    KoinApplication(application = {
        androidContext(context)
        modules(appModule, gsonModule)
    }) {
        SetColorDialog(
            profile = dayProfile(context),
            widget = Widget(),
            onAccept = {},
            onCancel = {},
        )
    }
}