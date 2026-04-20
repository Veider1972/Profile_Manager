package ru.veider.profilemanager.ui.assets.dialog

import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.*
import ru.veider.profilemanager.ui.theme.colorOnPrimary

@Composable
fun DialogWrapper(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 24.dp,
    color: Color = colorOnPrimary,
    border: BorderStroke = BorderStroke(width = 0.dp, color = Color.Transparent),
    usePlatformDefaultWidth: Boolean = true,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = true,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Dialog(
            onDismissRequest = {},
            properties = DialogProperties(
                dismissOnClickOutside = true,
                dismissOnBackPress = true,
                usePlatformDefaultWidth = usePlatformDefaultWidth
            )
        ) {
            Surface(
                modifier = modifier,
                shape = RoundedCornerShape(cornerRadius),
                color = color,
                border = border
            ) {
                content()
            }
        }
    }
}