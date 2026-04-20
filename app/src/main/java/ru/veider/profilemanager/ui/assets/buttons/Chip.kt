package ru.veider.profilemanager.ui.assets.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.theme.colorPrimary
import ru.veider.profilemanager.ui.theme.colorSurface
import ru.veider.profilemanager.utils.pressed

@Composable
fun Chip(
    imageId: Int,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()

    Surface(
        color = if (pressed) {
            if (selected)
                colorPrimary.pressed()
            else
                colorSurface.pressed()
        } else {
            if (selected)
                colorPrimary
            else
                colorSurface
        },
        shape = RoundedCornerShape(6.dp),
        shadowElevation = if (pressed) dimensionResource(id = R.dimen.half_elevation) else dimensionResource(id = R.dimen.single_padding),
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                role = Role.Button,
                onClick = onClick
            )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            Icon(
                painter = painterResource(imageId),
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
            )
        }
    }
}