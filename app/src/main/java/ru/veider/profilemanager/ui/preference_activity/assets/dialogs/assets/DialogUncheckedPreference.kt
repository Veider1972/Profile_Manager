package ru.veider.profilemanager.ui.preference_activity.assets.dialogs.assets

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.assets.enums.WidgetSymbolColor
import ru.veider.profilemanager.ui.preference_activity.assets.toDp
import ru.veider.profilemanager.ui.theme.colorInactive
import ru.veider.profilemanager.ui.theme.preferenceColor
import ru.veider.profilemanager.ui.theme.preferenceDesc
import ru.veider.profilemanager.ui.theme.preferenceTitle

@Composable
fun DialogUncheckedPreference(title: Int, desc: Int, onClick: () -> Unit) =
        DialogUncheckedPreference(title = stringResource(id = title), desc = stringResource(id = desc), onClick = { onClick() })

@Composable
fun DialogUncheckedPreference(title: String, desc: String, onClick: () -> Unit) {
    Row(modifier = Modifier
        .clickable(onClick = onClick)
        .padding(vertical = dimensionResource(id = R.dimen.half_padding))
    ) {
        Column {
            Text(text = title, style = preferenceTitle, maxLines = 1,
                 modifier = Modifier.padding(start = dimensionResource(id = R.dimen.double_padding),
                                             end = dimensionResource(id = R.dimen.single_padding)
                 )
            )
            Text(text = desc, style = preferenceDesc,
                 modifier = Modifier.padding(start = dimensionResource(id = R.dimen.double_padding),
                                             end = dimensionResource(id = R.dimen.single_padding)
                 )
            )
        }
    }
}

@Composable
fun DialogUncheckedPreference(title: String, desc: String, color: WidgetSymbolColor, onClick: () -> Unit) {

    val height = rememberSaveable { mutableStateOf(0) }

    Row(modifier = Modifier
        .clickable(onClick = onClick)
        .padding(vertical = dimensionResource(id = R.dimen.half_padding))
    ) {
        Column {
            Text(text = title, style = preferenceTitle, maxLines = 1,
                 modifier = Modifier.padding(start = dimensionResource(id = R.dimen.double_padding),
                                             end = dimensionResource(id = R.dimen.single_padding)
                 )
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "$desc ", style = preferenceDesc,
                     modifier = Modifier
                         .padding(start = dimensionResource(id = R.dimen.double_padding)
                         )
                         .onGloballyPositioned { position -> height.value = position.size.height }
                )
                Text(text = color.desc,
                     color = color.color,
                     style = preferenceColor
                )
//                Box(modifier = Modifier
//                    .height(height.value.toDp)
//                    .aspectRatio(1f)
//                    .background(color)
//                )
            }

        }
    }
}

@Composable
fun DialogUncheckedPreference(title: String, desc: String, postImage: Painter, onClick: () -> Unit) {

    val height = rememberSaveable { mutableStateOf(0) }

    Row(modifier = Modifier
        .clickable(onClick = onClick)
        .padding(vertical = dimensionResource(id = R.dimen.half_padding))
    ) {
        Column {
            Text(text = title, style = preferenceTitle, maxLines = 1,
                 modifier = Modifier.padding(start = dimensionResource(id = R.dimen.double_padding),
                                             end = dimensionResource(id = R.dimen.single_padding)
                 )
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = desc, style = preferenceDesc,
                     modifier = Modifier
                         .padding(start = dimensionResource(id = R.dimen.double_padding),
                                  end = dimensionResource(id = R.dimen.single_padding)
                         )
                         .onGloballyPositioned { position -> height.value = position.size.height }
                )
                Image(painter = postImage, contentDescription = "", modifier = Modifier.height(height.value.toDp))
            }

        }
    }
}

@Composable
fun DialogUncheckedHorizontalPreference(enabled: Boolean, preImage: Painter, title: String, desc: String, onClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
        .clickable(enabled = enabled, onClick = onClick)
        .fillMaxWidth()
        .padding(vertical = dimensionResource(id = R.dimen.single_padding))
    ) {
        Icon(painter = preImage,
             contentDescription = "",
             modifier = Modifier.padding(start = dimensionResource(id = R.dimen.double_padding)),
             tint = if (enabled) MaterialTheme.colors.onSurface else colorInactive
        )
        Text(text = title, style = preferenceTitle,
             modifier = Modifier
                 .padding(start = dimensionResource(id = R.dimen.single_padding),
                          end = dimensionResource(id = R.dimen.single_padding)
                 )
                 .weight(1f, true),
             color = if (enabled) MaterialTheme.colors.onSurface else colorInactive
        )
        if (desc.isNotEmpty())
            Text(text = desc, style = preferenceDesc,
                 modifier = Modifier
                     .padding(start = dimensionResource(id = R.dimen.single_padding),
                              end = dimensionResource(id = R.dimen.single_padding)
                     ),
                 color = if (enabled) MaterialTheme.colors.onSurface else colorInactive
            )
    }
}

@Composable
fun DialogUncheckedVerticalPreference(enabled: Boolean, preImage: Painter, title: String, desc: String, onClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
        .clickable(enabled = enabled, onClick = onClick)
        .fillMaxWidth()
        .padding(vertical = dimensionResource(id = R.dimen.single_padding))
    ) {
        Icon(painter = preImage,
             contentDescription = "",
             modifier = Modifier.padding(start = dimensionResource(id = R.dimen.double_padding)),
             tint = if (enabled) MaterialTheme.colors.onSurface else colorInactive
        )
        Column(verticalArrangement = Arrangement.Center, modifier = Modifier.weight(1f, true)) {
            Text(text = title, style = preferenceTitle,
                 modifier = Modifier
                     .padding(start = dimensionResource(id = R.dimen.single_padding),
                              end = dimensionResource(id = R.dimen.single_padding)
                     ),
                 color = if (enabled) MaterialTheme.colors.onSurface else colorInactive
            )
            if (desc.isNotEmpty())
                Text(text = desc, style = preferenceDesc,
                     modifier = Modifier
                         .padding(start = dimensionResource(id = R.dimen.single_padding),
                                  end = dimensionResource(id = R.dimen.single_padding)
                         ),
                     color = if (enabled) MaterialTheme.colors.onSurface else colorInactive
                )
        }
    }
}