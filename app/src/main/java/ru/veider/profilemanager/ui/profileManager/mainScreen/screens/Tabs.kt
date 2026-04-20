package ru.veider.profilemanager.ui.profileManager.mainScreen.screens

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import com.arttttt.nav3router.Router
import kotlinx.coroutines.*
import org.koin.android.ext.koin.androidContext
import org.koin.compose.KoinApplication
import ru.veider.profilemanager.data.dayProfile
import ru.veider.profilemanager.di.appModule
import ru.veider.profilemanager.di.gsonModule
import ru.veider.profilemanager.domain.ProfileTabItem
import ru.veider.profilemanager.domain.ProfileType
import ru.veider.profilemanager.domain.Screen
import ru.veider.profilemanager.domain.Tab
import ru.veider.profilemanager.domain.Widget
import ru.veider.profilemanager.ui.theme.colorOnSurface
import ru.veider.profilemanager.ui.theme.colorPrimary
import ru.veider.profilemanager.ui.theme.colorPrimaryDark
import ru.veider.profilemanager.ui.theme.colorTransparent
import ru.veider.profilemanager.ui.theme.colorWhite
import ru.veider.profilemanager.ui.theme.textStyle_18_500

@Composable
fun <T : Tab> Tabs(tabs: List<T>, pagerState: PagerState) {


    val scope = rememberCoroutineScope()
    // OR ScrollableTabRow()
    SecondaryTabRow(
        modifier = Modifier.height(IntrinsicSize.Max),
        selectedTabIndex = pagerState.currentPage,
        containerColor = colorTransparent,
        contentColor = colorWhite,
        indicator = {
            TabRowDefaults.SecondaryIndicator(
                color = colorPrimary.copy(alpha = 0.2f),
                modifier = Modifier.fillMaxHeight().tabIndicatorOffset(selectedTabIndex = pagerState.currentPage, matchContentSize = false)
            )
            TabRowDefaults.SecondaryIndicator(
                color = colorPrimaryDark,
                modifier = Modifier.tabIndicatorOffset(selectedTabIndex = pagerState.currentPage, matchContentSize = false)
            )
        },
        divider = {
            HorizontalDivider(thickness = 1.dp, color = colorPrimary)
        }) {
        tabs.forEachIndexed { index, tab ->
            // OR Tab()
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.scrollToPage(index)
                    }
                },
                text = {
                    AnimatedContent(
                        targetState = pagerState.currentPage == index
                    ) { isSelected ->
                        Text(
                            text = stringResource(tab.title),
                            style = textStyle_18_500,
                            color = if (isSelected) colorOnSurface else colorPrimaryDark,
                            maxLines = 1
                        )

                    }

                },
                modifier = Modifier
                    .background(
                        color = colorTransparent
                    ),
            )
        }
    }
}

@Preview(showBackground = true, apiLevel = 33)
@Composable
fun TabsPreview() {
    val context = LocalContext.current
    val router = remember{ Router<Screen>() }
    val tabs = listOf(
        ProfileTabItem.ProfileWidget(router, dayProfile(context), {}, Widget(), {}),
        ProfileTabItem.ProfileTools(router, dayProfile(context), {})
    )
    val pagerState = rememberPagerState { tabs.size }
    KoinApplication(application = {
        androidContext(context)
        modules(appModule, gsonModule)
    }) {
        Tabs(tabs = tabs, pagerState = pagerState)
    }
}