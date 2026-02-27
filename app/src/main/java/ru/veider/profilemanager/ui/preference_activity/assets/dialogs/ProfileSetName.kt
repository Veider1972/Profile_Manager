package ru.veider.profilemanager.ui.preference_activity.assets.dialogs

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import ru.veider.profilemanager.R
import ru.veider.profilemanager.domain.Profile
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogAcceptCancelButtons
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogPreferenceTitle
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogWrapper
import ru.veider.profilemanager.ui.theme.dialogText

@Composable
fun ProfileSetName(
    name: String,
    onDismiss: () -> Unit,
    onAccept: (String) -> Unit,
    onCancel: () -> Unit
) {

    val context = LocalContext.current

    var profileName by remember {
        mutableStateOf(name)
    }

    DialogWrapper(onDismiss = onDismiss) {
        DialogPreferenceTitle(text = stringResource(id = R.string.dialog_profile_set_name_title))
        TextField(
            value = profileName,
            onValueChange = { value -> profileName = value },
            textStyle = dialogText,
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.single_padding)),
            singleLine = true
        )
        val text = stringResource(R.string.dialog_profile_set_name_not_empty)
        DialogAcceptCancelButtons(
            accept = {
                if (profileName.isNotEmpty())
                    onAccept(profileName)
                else
                    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
            },
            cancel = { onCancel() })
    }

}
