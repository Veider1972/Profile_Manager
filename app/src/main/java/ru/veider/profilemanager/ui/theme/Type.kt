package ru.veider.profilemanager.ui.theme

import androidx.compose.material.Typography
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
val selectDialogModeFont get() = TextStyle(fontFamily = FontFamily(Font(R.font.roboto_w400)), fontSize = 18.sp)

// Preference Activity
val preferenceTabFont get() = TextStyle(fontFamily = FontFamily(Font(R.font.roboto_w400)), fontSize = 15.sp)
val preferenceCaption get() = TextStyle(fontFamily = FontFamily(Font(R.font.roboto_w400)), fontSize = 17.sp)
val preferenceTitle get() = TextStyle(fontFamily = FontFamily(Font(R.font.roboto_w400)), fontSize = 15.sp)
val preferenceDesc get() = TextStyle(fontFamily = FontFamily(Font(R.font.roboto_w300)), fontSize = 12.sp)
val preferenceColor get() = TextStyle(fontFamily = FontFamily(Font(R.font.roboto_w900)), fontSize = 12.sp)

// Dialogs
val dialogText get() = TextStyle(fontFamily = FontFamily(Font(R.font.roboto_w400)), fontSize = 15.sp)
val dialogTitle get() = TextStyle(fontFamily = FontFamily(Font(R.font.roboto_w700)), fontSize = 17.sp)
val dialogTimeSelector get() = TextStyle(fontFamily = FontFamily(Font(R.font.roboto_w400)), fontSize = 60.sp)
val dialogTimeMinSec get() = TextStyle(fontFamily = FontFamily(Font(R.font.roboto_w400)), fontSize = 25.sp)