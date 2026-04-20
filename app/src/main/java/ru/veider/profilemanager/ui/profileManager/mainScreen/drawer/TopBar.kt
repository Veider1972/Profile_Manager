package ru.veider.profilemanager.ui.profileManager.mainScreen.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.theme.colorPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onOpenDrawer: ()->Unit,
    onSettings: ()->Unit
) {

    val scope = rememberCoroutineScope()

    TopAppBar(
        windowInsets = TopAppBarDefaults.windowInsets,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorPrimary
        ),
        navigationIcon = {
            Image(
                painter = painterResource(R.drawable.navigation),
                modifier = Modifier
                    .minimumInteractiveComponentSize()
                    .clickable { onOpenDrawer() },
                contentDescription = null
            )
        },
        title = {
            Text(
                text = stringResource(R.string.app_name)
            )
        },
        actions = {
            Image(
                imageVector = Icons.Filled.Settings,
                contentDescription = null,
                modifier = Modifier
                    .minimumInteractiveComponentSize()
                    .clickable { onSettings() }
            )
        }
    )
}

@Preview
@Composable
private fun TopBarPreview() {
    TopBar(
        {},
        {}
    )
}