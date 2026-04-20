package ru.veider.profilemanager.ui.assets.sliders

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import org.koin.android.ext.koin.androidContext
import org.koin.compose.KoinApplication
import ru.veider.profilemanager.di.*
import ru.veider.profilemanager.ui.theme.colorTransparent
import kotlin.math.roundToInt

@Composable
fun TransparencySlider(
    modifier: Modifier = Modifier,
    value: Float,
    onChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    delta: Float,
    background: Brush,
    border: Color = colorTransparent,
    height: Dp = 24.dp,
    pointerScope: @Composable () -> Unit
) {

    val density = LocalDensity.current
    val pointer by rememberUpdatedState(newValue = density.run { height.toPx() })
    var width by remember { mutableFloatStateOf(0f) }
    val end by rememberUpdatedState(newValue = width - pointer)
    val steps by rememberUpdatedState(newValue = (valueRange.endInclusive - valueRange.start) / delta)
    val sliderDelta by rememberUpdatedState(newValue = end / steps)
    var alpha by remember { mutableFloatStateOf(1f) }
    val animatedAlpha by animateFloatAsState(targetValue = alpha)

    var currentPosition by remember { mutableFloatStateOf(value) }

    var offsetX by remember { mutableFloatStateOf(0f) }

    val interactionSource = remember { MutableInteractionSource() }

    var isDragging by remember { mutableStateOf(false) }
    val dragState = rememberDraggableState {
        val offset = offsetX + it
        offsetX = when {
            offset > width - pointer -> width - pointer
            offset < 0 -> 0f
            else -> offset
        }
    }

    LaunchedEffect(isDragging) {
        if (!isDragging) {
            offsetX = (currentPosition - valueRange.start) / delta * sliderDelta
            currentPosition = (offsetX / sliderDelta).roundToInt() * delta + valueRange.start
            onChange(currentPosition)
        }
    }

    LaunchedEffect(value) {
        if (!isDragging) {
            currentPosition = value
            offsetX = (currentPosition - valueRange.start) / delta * sliderDelta
        }
    }

    Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = modifier
                .onPlaced {
                    width = it.size.width.toFloat()
                    offsetX = (currentPosition - valueRange.start) / delta * sliderDelta
                },
            contentAlignment = Alignment.TopStart
        ) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(height / 2))
                    .background(background)
                    .border(
                        width = 1.dp,
                        color = border,
                        shape = RoundedCornerShape(height / 2)
                    )
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height)
                        .background(
                            brush = background
                        )
                )
            }

            Box(
                modifier = Modifier
                    .offset { IntOffset(offsetX.roundToInt(), 0) }
                    .size(height)
                    .draggable(
                        state = dragState,
                        orientation = Orientation.Horizontal,
                        onDragStarted = { isDragging = true },
                        interactionSource = interactionSource,
                        onDragStopped = {
                            currentPosition =
                                (offsetX / sliderDelta).roundToInt() * delta + valueRange.start
                            onChange(currentPosition)
                            offsetX = (currentPosition - valueRange.start) / delta * sliderDelta
                            isDragging = false
                        }
                    )

            ) {
                Box(
                    modifier = Modifier
                        .alpha(if (LocalInspectionMode.current) 1f else animatedAlpha)
                ) {
                    pointerScope()
                }
            }
        }
    }
}

@Preview(widthDp = 500, heightDp = 200)
@Composable
private fun Preview() {
    val context = LocalContext.current
    KoinApplication(application = {
        androidContext(context)
        modules(appModule, gsonModule)
    }) {
        Box(
            modifier = Modifier
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            var position by remember { mutableFloatStateOf(100f) }
            Column {
                TransparencySlider(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth(),
                    value = position,
                    onChange = { position = it },
                    valueRange = 0f..255f,
                    delta = 1f,
                    background = Brush.linearGradient(listOf(Color.Black, Color.White)),
                    height = 24.dp,
                    pointerScope = { Pointer(24.dp) }
                )
            }
        }
    }
}