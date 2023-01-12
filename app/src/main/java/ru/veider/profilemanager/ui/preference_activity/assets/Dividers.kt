package ru.veider.profilemanager.ui.preference_activity.assets

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import ru.veider.profilemanager.R

@Composable
fun HorizontalThickDivider(padding: Dp, color: Color? = null) {
    HorizontalDivider(padding = padding, thickness = dimensionResource(id = R.dimen.double_spacer_width), color = color)
}

@Composable
fun HorizontalThingDivider(padding: Dp, color: Color? = null) {
    HorizontalDivider(padding = padding, thickness = dimensionResource(id = R.dimen.spacer_width), color = color)
}

@Composable
fun HorizontalDivider(padding: Dp, thickness: Dp, color: Color? = null) {
    if (color == null)
        Divider(thickness = thickness, modifier = Modifier.padding(horizontal = padding))
    else
        Divider(thickness = thickness, modifier = Modifier.padding(horizontal = padding), color = color)
}
