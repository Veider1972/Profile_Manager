package ru.veider.profilemanager.ui.preference_activity.assets.dialogs

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import org.koin.androidx.compose.koinViewModel
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogAcceptCancelButtons
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogPreferenceTitle
import ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets.DialogWrapper
import ru.veider.profilemanager.ui.theme.dialogText
import ru.veider.profilemanager.viewmodel.PreferenceViewModel

@Composable
fun ProfileSetName(onDismiss: () -> Unit,
                              onAccept: (String) -> Unit,
                              onCancel: () -> Unit) {

    val viewModel: PreferenceViewModel = koinViewModel()
    val state by viewModel.profileState.collectAsState()
    val context = LocalContext.current

    val profileName = remember {
        mutableStateOf(state.name)
    }

    DialogWrapper(onDismiss = onDismiss) {
        DialogPreferenceTitle(text = stringResource(id = R.string.dialog_profile_set_name_title))
        TextField(value = profileName.value,
                  onValueChange = {value -> profileName.value = value},
                  textStyle = dialogText,
                  modifier = Modifier.padding(start = dimensionResource(id = R.dimen.single_padding)),
                  singleLine = true)
        DialogAcceptCancelButtons(accept = {
            if (profileName.value.isNotEmpty())
                onAccept(profileName.value)
            else
                Toast.makeText(context, context.getString(R.string.dialog_profile_set_name_not_empty), Toast.LENGTH_LONG).show()
        }, cancel = { onCancel() })
    }

}
