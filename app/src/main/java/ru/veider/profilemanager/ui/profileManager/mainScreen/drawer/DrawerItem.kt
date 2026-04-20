package ru.veider.profilemanager.ui.profileManager.mainScreen.drawer

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.theme.colorTransparent
import ru.veider.profilemanager.ui.theme.singleCorner

@Composable
fun DrawerItem(
    @DrawableRes icon: Int,
    @StringRes label: Int,
    selected: Boolean,
    onClick: () -> Unit
) {
    NavigationDrawerItem(
        icon = {
            Icon(
                painter = painterResource(icon),
                contentDescription = null
            )
        },
        shape = RoundedCornerShape(singleCorner),
        colors = NavigationDrawerItemDefaults.colors(
            selectedContainerColor = colorTransparent
        ),
        label = {
            Text(
                text = stringResource(label),
                fontSize = 16.sp,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            )
        },
        onClick = onClick,
        selected = selected
    )
}

@Preview
@Composable
private fun Preview() {
    DrawerItem(
        icon = R.drawable.navigation,
        label = R.string.profiles,
        selected = true,
        onClick = {}
    )
}