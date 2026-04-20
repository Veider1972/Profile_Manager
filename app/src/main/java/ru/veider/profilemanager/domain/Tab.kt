package ru.veider.profilemanager.domain

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.arttttt.nav3router.Router

interface Tab {
    @get:StringRes val title: Int
    val screen: @Composable (() -> Unit)
}