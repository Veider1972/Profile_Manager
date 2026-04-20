package ru.veider.profilemanager.ui.profileManager.profileEditor.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import org.koin.android.ext.koin.androidContext
import org.koin.compose.KoinApplication
import ru.veider.profilemanager.R
import ru.veider.profilemanager.data.dayProfile
import ru.veider.profilemanager.di.*
import ru.veider.profilemanager.domain.*
import ru.veider.profilemanager.ui.assets.buttons.*
import ru.veider.profilemanager.ui.assets.dialog.DialogWrapper
import ru.veider.profilemanager.ui.assets.elements.DialogTitle
import ru.veider.profilemanager.ui.profileManager.assets.dialogs.assets.DrawWidget
import ru.veider.profilemanager.ui.theme.*

@Composable
fun SetSymbolDialog(
    profile: Profile,
    widget: Widget,
    onAccept: (Profile) -> Unit,
    onCancel: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var symbol by remember { mutableStateOf(profile.symbol) }

    DialogWrapper() {
        Column(
            modifier = Modifier.width(IntrinsicSize.Max)
        ){
            DialogTitle(title = stringResource(id = R.string.widget_color))
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(singlePadding),
                contentAlignment = Alignment.Center) {
                Image(
                    painterResource(R.drawable.transparent_background),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(LocalMinimumInteractiveComponentSize.current * 1.5f)
                )
                DrawWidget(
                    size = LocalMinimumInteractiveComponentSize.current * 1.5f,
                    widgetBackground = widget.background,
                    color = profile.color,
                    symbol = symbol
                )
            }
            Box(modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center){
                FlowRow(
                    modifier = Modifier
                        .padding(singlePadding),
                    maxItemsInEachRow = 4
                ){
                    Symbol.entries.forEach {
                        Chip(
                            imageId = it.symbol,
                            selected = symbol == it,
                            onClick = {
                                symbol = it
                            },
                            modifier = Modifier.padding(halfPadding)
                        )
                    }
                }
            }

            DialogButtons(
                modifier = Modifier.fillMaxWidth(),
                acceptEnabled = symbol != profile.symbol,
                onAccept = {
                    onAccept(profile.copy(symbol = symbol))
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
        SetSymbolDialog(
            profile = dayProfile(context),
            widget = Widget(),
            onAccept = {},
            onCancel = {},
        )
    }
}