package ru.veider.profilemanager.ui.profileManager.profileEditor.dialogs

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.*
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
import ru.veider.profilemanager.ui.theme.*
import ru.veider.profilemanager.utils.*

@Composable
fun SetGradientDialog(
    profile: Profile,
    widget: Widget,
    onAccept: (Widget) -> Unit,
    onCancel: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val density = LocalDensity.current
    val context = LocalContext.current
    val controller = rememberColorPickerController()
    var background by remember { mutableStateOf(widget.background) }
    var currentIsFirst by remember { mutableStateOf(true) }
    val wheelColor = colorResource(R.color.color_primary_dark)
    val startColor = colorPrimary
    val endColor = colorPrimaryDark
    LaunchedEffect(key1 = Unit) {
        controller.wheelRadius = 10.dp
        controller.wheelColor = wheelColor
        controller.selectByColor(background.startColor(context), true)
    }

    val elementHeight = 24.dp


    val pointer = remember { context.pointer(density.run { elementHeight.toPx() }) }

    DialogWrapper(
        usePlatformDefaultWidth = false
    ) {
        Column(
            modifier = Modifier.width(IntrinsicSize.Max)
        ) {
            DialogTitle(title = stringResource(id = R.string.widget_color))
            Row(
                modifier = Modifier
                    .padding(top = singlePadding),
                horizontalArrangement = Arrangement.spacedBy(halfPadding)
            ) {
                ColorButton(
                    modifier = Modifier.padding(start = singlePadding),
                    text = stringResource(R.string.white_color),
                    color = colorWhite,
                    selected = background is WidgetBackground.White,
                    onClick = {
                        background = WidgetBackground.White
                    }
                )
                ColorButton(
                    text = stringResource(R.string.gray_color),
                    color = colorWhite,
                    selected = background is WidgetBackground.Gray,
                    onClick = {
                        background = WidgetBackground.Gray
                    }
                )
                ColorButton(
                    text = stringResource(R.string.black_color),
                    color = colorWhite,
                    selected = background is WidgetBackground.Black,
                    onClick = {
                        background = WidgetBackground.Black
                    }
                )
                ColorButton(
                    text = stringResource(R.string.transparent_color),
                    color = colorWhite,
                    selected = background is WidgetBackground.Transparent,
                    onClick = {
                        background = WidgetBackground.Transparent
                    }
                )
                ColorButton(
                    modifier = Modifier.padding(end = singlePadding),
                    text = stringResource(R.string.custom_color),
                    color = colorWhite,
                    selected = background is WidgetBackground.Custom,
                    onClick = {
                        val oldBackground = background
                        background = WidgetBackground.Custom(oldBackground.startColor(context), oldBackground.endColor(context), background.transparency)
                    }
                )

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(singlePadding),
                horizontalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.End
                ) {
                    AnimatedVisibility(
                        visible = background is WidgetBackground.Custom,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(halfPadding),
                            modifier = Modifier
                                .padding(end = singlePadding)
                                .background(
                                    color = if (currentIsFirst) colorPrimary.copy(alpha = 0.2f) else colorTransparent,
                                    shape = RoundedCornerShape(singleCorner)
                                )
                                .border(
                                    width = 1.dp,
                                    color = if (currentIsFirst) colorPrimary else colorTransparent,
                                    shape = RoundedCornerShape(singleCorner)
                                )
                                .padding(halfPadding)
                        ) {
                            var height by remember { mutableStateOf(0.dp) }
                            ColorButton(
                                text = stringResource(R.string.start),
                                color = colorWhite,
                                selected = false,
                                onClick = {
                                    currentIsFirst = true
                                    controller.selectByColor(background.startColor(context), true)
                                },
                                modifier = Modifier
                                    .onPlaced {
                                        height = density.run { it.size.height.toDp() }
                                    }
                            )
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(singleCorner))
                                    .size(height)
                                    .background(color = background.startColor(context))
                                    .border(
                                        width = 1.dp,
                                        color = Color.Gray,
                                        shape = RoundedCornerShape(singleCorner)
                                    )
                            ) {}
                        }
                    }
                }

                Box {
                    Image(
                        painterResource(R.drawable.transparent_background),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(LocalMinimumInteractiveComponentSize.current * 1.5f)
                    )
                    DrawWidget(
                        size = LocalMinimumInteractiveComponentSize.current * 1.5f,
                        widgetBackground = background,
                        color = profile.color,
                        symbol = profile.symbol
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(start = singlePadding)
                        .weight(1f)
                        .align(Alignment.Bottom)

                ) {
                    AnimatedVisibility(
                        visible = background is WidgetBackground.Custom,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(halfPadding),
                            modifier = Modifier
                                .background(
                                    color = if (!currentIsFirst) colorPrimary.copy(alpha = 0.2f) else colorTransparent,
                                    shape = RoundedCornerShape(singleCorner)
                                )
                                .border(
                                    width = 1.dp,
                                    color = if (!currentIsFirst) colorPrimary else colorTransparent,
                                    shape = RoundedCornerShape(singleCorner)
                                )
                                .padding(halfPadding)
                        ) {
                            var height by remember { mutableStateOf(0.dp) }
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(singleCorner))
                                    .size(height)
                                    .background(color = background.endColor(context))
                                    .border(
                                        width = 1.dp,
                                        color = Color.Gray,
                                        shape = RoundedCornerShape(singleCorner)
                                    )
                            ) {}
                            ColorButton(
                                text = stringResource(R.string.finish),
                                color = colorWhite,
                                selected = false,
                                onClick = {
                                    currentIsFirst = false
                                    controller.selectByColor(background.endColor(context), true)
                                },
                                modifier = Modifier
                                    .onPlaced {
                                        height = density.run { it.size.height.toDp() }
                                    }
                            )
                        }

                    }
                }


            }
            AnimatedVisibility(
                visible = background is WidgetBackground.Custom,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Column {

                    HsvColorPicker(
                        initialColor = background.startColor(context),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = singlePadding)
                            .aspectRatio(1f),
                        controller = controller,
                        wheelImageBitmap = pointer.asImageBitmap(),
                        onColorChanged = { colorEnvelope: ColorEnvelope ->
                            scope.launch {
                                val color = colorEnvelope.color.copy(alpha = 1f)
                                val alpha = colorEnvelope.color.alpha
                                if (currentIsFirst)
                                    background = WidgetBackground.Custom(color, background.endColor(context), alpha)
                                else
                                    background = WidgetBackground.Custom(background.startColor(context), color, alpha)
                            }
                        }
                    )
                    TransparencySlider(
                        modifier = Modifier.padding(start = singlePadding, top = singlePadding, end = singlePadding),
                        value = 1f - if (currentIsFirst) background.startColor(context).toCmyk().black else background.endColor(context).toCmyk().black,
                        onChange = {

                            controller.selectByColor(controller.selectedColor.value.toCmyk().copy(black = 1f - it).toColor(), true)
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
                    TransparencySlider(
                        modifier = Modifier
                            .padding(start = singlePadding, top = singlePadding, end = singlePadding)
                            .clip(RoundedCornerShape(12.dp))
                            .drawBehind {
                                val height = size.height / 2
                                val width = size.width
                                var x = 0f
                                var odd = 0
                                while (x < width) {
                                    drawRect(
                                        color = Color.LightGray,
                                        topLeft = Offset(x, (odd++ % 2) * height),
                                        size = Size(height, height)
                                    )
                                    x += height
                                }
                            },
                        value = controller.wheelColor.toCmyk().alpha,
                        onChange = {
                            controller.selectByColor(controller.selectedColor.value.toCmyk().copy(alpha = it).toColor(), true)
                        },
                        valueRange = 0f..1f,
                        delta = 0.01f,
                        background = Brush.linearGradient(
                            listOf(
                                controller.selectedColor.value.toCmyk().copy(alpha = 0f).toColor(),
                                controller.selectedColor.value.toCmyk().copy(alpha = 1f).toColor()
                            )
                        ),
                        height = elementHeight,
                        pointerScope = { Pointer(elementHeight) }
                    )
                }
            }


            DialogButtons(
                modifier = Modifier.fillMaxWidth(),
                acceptEnabled =
                    background.startColor(context) != widget.background.startColor(context)
                            || background.endColor(context) != widget.background.endColor(context)
                            || background.transparency != widget.background.transparency,
                onAccept = {
                    onAccept(widget.copy(background = background))
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
        SetGradientDialog(
            profile = dayProfile(context),
            widget = Widget().copy(background = WidgetBackground.Custom(Color.Gray, Color.White, 0.5f)),
            onAccept = {},
            onCancel = {},
        )
    }
}