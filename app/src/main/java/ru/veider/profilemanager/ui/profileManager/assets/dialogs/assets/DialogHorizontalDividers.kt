package ru.veider.profilemanager.ui.profileManager.assets.dialogs.assets

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import ru.veider.profilemanager.R

@Composable
fun DialogHorizontalThickDivider(padding: Dp) {
    DialogHorizontalDivider(padding = padding, thickness = dimensionResource(id = R.dimen.double_spacer_width))
}

@Composable
fun DialogHorizontalThingDivider(padding: Dp) {
    DialogHorizontalDivider(padding = padding, thickness = dimensionResource(id = R.dimen.single_spacer_width))
}

@Composable
fun DialogHorizontalDivider(padding: Dp, thickness: Dp) {
    Divider(thickness = thickness, modifier = Modifier.padding(horizontal = padding),
            color = MaterialTheme.colorScheme.primary
    )
}
