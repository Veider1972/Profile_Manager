package ru.veider.profilemanager.ui.profileManager.mainScreen.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.veider.profilemanager.R
import ru.veider.profilemanager.domain.Screen
import ru.veider.profilemanager.ui.theme.*

@Composable
fun DrawerSheet(
    state: Screen,
    onStateChange: (Screen) -> Unit,
) {

    val gradient = listOf(colorPrimary, colorPrimaryDark)
    val density = LocalDensity.current
    val iconOffsetX = 16.dp
    val iconOffsetY = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val iconSize = 100.dp
    val spotCenterX = remember { density.run { (iconOffsetX + iconSize / 2).toPx() }  }
    val spotCenterY = remember { density.run { (iconOffsetY + iconSize / 2).toPx() }  }
    val radius = remember { density.run { (iconSize * 3).toPx() }  }

    ModalDrawerSheet(
        modifier = Modifier.width(IntrinsicSize.Max),
        windowInsets = WindowInsets(0.dp,0.dp,0.dp,0.dp)
    ) {
        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth().background(brush = Brush.radialGradient(colors = gradient, center = Offset(spotCenterX, spotCenterY), radius = radius))
            ) {
                Image(
                    painter = painterResource(R.drawable.icon_big),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(
                            start = iconOffsetX,
                            top = iconOffsetY
                        )
                        .size(iconSize)
                )
                Text(
                    text = stringResource(R.string.app_name).uppercase(),
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(start = iconOffsetX, top = 10.dp)
                )
                Text(
                    text = stringResource(R.string.my_email),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = iconOffsetX, bottom = 16.dp)
                )
            }
        }
        DrawerItem(
            icon = R.drawable.navigation,
            label = R.string.profiles,
            selected = state == Screen.Profiles,
            onClick = { onStateChange(Screen.Profiles) }

        )
//        DrawerItem(
//            icon = R.drawable.icon_settings,
//            label = R.string.menu_settings,
//            selected = state == Screen.Settings,
//            onClick = { onStateChange(Screen.Settings) }
//
//        )
//        DrawerItem(
//            icon = R.drawable.icon_about,
//            label = R.string.menu_about,
//            selected = state == Screen.About,
//            onClick = { onStateChange(Screen.About) }
//
//        )

    }
}

@Preview
@Composable
private fun DrawerSheetPreview() {
    DrawerSheet(
        state = Screen.Profiles,
        onStateChange = {}
    )
}