package ru.veider.profilemanager.ui.preference_activity.main_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.main_screen.tabs.ModesTab
import ru.veider.profilemanager.ui.preference_activity.main_screen.tabs.ToolsTab
import ru.veider.profilemanager.ui.preference_activity.main_screen.tabs.WidgetTab
import ru.veider.profilemanager.ui.theme.preferenceTabFont

@Composable
fun MainTabLayout(navController: NavController) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 3 })
    Column() {
        MainTabs(pagerState = pagerState)
        MainTabsContent(pagerState = pagerState, navController)
    }
}

@Composable
fun MainTabs(pagerState: PagerState) {
    val list = listOf(
        stringResource(id = R.string.preference_modes),
        stringResource(id = R.string.preference_tools),
        stringResource(id = R.string.preference_widget)
    )
    val scope = rememberCoroutineScope()
    SecondaryTabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = Modifier.fillMaxWidth(),
        indicator = { /*tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = dimensionResource(id = R.dimen.tab_indicator_height),
                color = MaterialTheme.colors.primary
            )*/
        }
    ) {
        list.forEachIndexed { index, _ ->
            Tab(
                text = { Text(text = list[index], style = preferenceTabFont, maxLines = 1) },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.scrollToPage(index)
                    }
                }
            )
        }
    }
}

@Composable
fun MainTabsContent(pagerState: PagerState, navController: NavController) {
    HorizontalPager(state = pagerState) { page ->
        when (page) {
            0 -> ModesTab(navController)
            1 -> ToolsTab()
            2 -> WidgetTab()
        }
    }
}