package ru.veider.eventsreminder.ui.patterns

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.veider.profilemanager.ui.assets.buttons.DialogButton
import ru.veider.profilemanager.ui.assets.dialog.DialogWrapper
import ru.veider.profilemanager.ui.assets.elements.DialogTitle
import ru.veider.profilemanager.ui.theme.doublePadding

@Composable
fun OneButtonDialog(
    title: String,
    message: String,
    modifier: Modifier = Modifier,
    buttonText: String,
    onClick: () -> Unit
) {
    DialogWrapper {
        Column(
            modifier = modifier.width(IntrinsicSize.Max)
        ) {
            DialogTitle(title = title)
            Text(
                modifier = Modifier.padding(doublePadding),
                text = message
            )
            DialogButton(
                buttonText = buttonText.uppercase(),
                onClick = onClick
            )
        }
    }

}

@Preview
@Composable
private fun OneButtonDialogShow() {
    OneButtonDialog(
        title = "Ошибка!",
        message = "Название события не должно быть пустым",
        buttonText = "Закрыть",
        onClick = {}
    )
}