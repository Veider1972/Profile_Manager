package ru.veider.profilemanager.ui.profileManager.profileEditor.dialogs

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.koin.android.ext.koin.androidContext
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import ru.veider.profilemanager.R
import ru.veider.profilemanager.data.defaultProfiles
import ru.veider.profilemanager.di.appModule
import ru.veider.profilemanager.di.gsonModule
import ru.veider.profilemanager.domain.Profile
import ru.veider.profilemanager.domain.ProfileType
import ru.veider.profilemanager.repo.Preference
import ru.veider.profilemanager.ui.assets.buttons.DialogButtons
import ru.veider.profilemanager.ui.assets.elements.DialogTitle
import ru.veider.profilemanager.ui.profileManager.assets.dialogs.assets.DialogWrapper
import ru.veider.profilemanager.ui.profileManager.assets.dialogs.assets.DrawWidget
import ru.veider.profilemanager.ui.theme.colorPrimary
import ru.veider.profilemanager.ui.theme.halfPadding
import ru.veider.profilemanager.ui.theme.singleCorner
import ru.veider.profilemanager.ui.theme.singlePadding

@Composable
fun AddProfileDialog(
    profiles: List<Profile>,
    onAccept: (Profile) -> Unit,
    onCancel: () -> Unit
) {

    var selected by remember { mutableStateOf<ProfileType?>(null) }

    DialogWrapper(
        onDismiss = onCancel
    ) {
        Column() {
            DialogTitle(stringResource(R.string.free_profiles))
            Column(
                modifier = Modifier
                    .padding(vertical = singlePadding)
                    .verticalScroll(
                        state = rememberScrollState()
                    ),
                verticalArrangement = Arrangement.spacedBy(halfPadding)
            ) {
                profiles.forEach {
                    ProfileItem(
                        profile = it,
                        selected = it.type == selected,
                        onClick = { selected = it.type })
                }
            }
            DialogButtons(
                modifier = Modifier.fillMaxWidth(),
                acceptButtonText = stringResource(R.string.accept),
                acceptEnabled = selected != null,
                onAccept = { profiles.firstOrNull { it.type == selected }?.let { onAccept(it) } },
                cancelButtonText = stringResource(R.string.cancel),
                onCancel = onCancel
            )
        }
    }
}

@Composable
private fun ProfileItem(
    modifier: Modifier = Modifier,
    profile: Profile,
    selected: Boolean,
    onClick: () -> Unit
) {



    val prefs: Preference = koinInject()
    val widget by prefs.widget.collectAsState()

    LaunchedEffect(Unit) {
        Log.d("ProfileItem", "profile=$profile widget=$widget")
    }

    Row(
        modifier = modifier
            .padding(horizontal = singlePadding)
            .clip(RoundedCornerShape(singleCorner))
            .then(
                if (selected)
                    Modifier
                        .background(
                            color = colorPrimary.copy(alpha = 0.5f)
                        )
                else
                    Modifier
            )
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(halfPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            DrawWidget(
                size = LocalMinimumInteractiveComponentSize.current,
                widgetBackground = widget.background,
                color = profile.color,
                symbol = profile.symbol,
                onClick = {}
            )
            Text(
                modifier = Modifier.padding(start = singlePadding),
                text = profile.name
            )
        }

    }
}

@Preview
@Composable
private fun Preview() {
    val context = LocalContext.current
    KoinApplication(application = {
        androidContext(context)
        modules(appModule, gsonModule)
    }) {
        AddProfileDialog(
            profiles = defaultProfiles(context),
            onAccept = {},
            onCancel = {}
        )
    }
}