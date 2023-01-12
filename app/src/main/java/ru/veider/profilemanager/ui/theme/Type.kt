package ru.veider.profilemanager.ui.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.veider.profilemanager.R

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)

// Select Activity
val selectDialogModeFont @Composable get() = TextStyle(fontFamily = FontFamily(Font(R.font.roboto_w400)), fontSize = 18.sp)

// Preference Activity
val preferenceTabFont @Composable get() = TextStyle(fontFamily = FontFamily(Font(R.font.roboto_w400)), fontSize = 15.sp)
val preferenceCaption @Composable get() = TextStyle(fontFamily = FontFamily(Font(R.font.roboto_w400)), fontSize = 17.sp)
val preferenceTitle @Composable get() = TextStyle(fontFamily = FontFamily(Font(R.font.roboto_w400)), fontSize = 15.sp)
val preferenceDesc @Composable get() = TextStyle(fontFamily = FontFamily(Font(R.font.roboto_w300)), fontSize = 12.sp)

// Dialogs
val dialogText @Composable get() = TextStyle(fontFamily = FontFamily(Font(R.font.roboto_w400)), fontSize = 15.sp)
val dialogTitle @Composable get() = TextStyle(fontFamily = FontFamily(Font(R.font.roboto_w700)), fontSize = 17.sp)