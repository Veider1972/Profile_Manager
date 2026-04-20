package ru.veider.profilemanager.ui.assets.elements

import androidx.compose.foundation.layout.height
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.veider.profilemanager.domain.ProfileType
import ru.veider.profilemanager.ui.theme.colorPrimary
import ru.veider.profilemanager.utils.updateBy

@Composable
fun GreenSwitch(
    checked: Boolean,
    enabled: Boolean = true,
    onCheckedChange: (Boolean) -> Unit
){
    Switch(
        checked = checked,
        colors = SwitchDefaults.colors(
            checkedTrackColor = colorPrimary
        ),
        enabled = enabled,
        onCheckedChange = onCheckedChange,
        modifier = Modifier.height(LocalMinimumInteractiveComponentSize.current)
    )
}