package ru.veider.profilemanager.ui.preference_activity

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.preference_activity.main_screen.tabs.ModesTab
import ru.veider.profilemanager.ui.preference_activity.main_screen.tabs.ToolsTab
import ru.veider.profilemanager.ui.preference_activity.main_screen.tabs.WidgetTab
import ru.veider.profilemanager.ui.theme.preferenceTabFont

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainTabLayout(navController: NavController) {
    val pagerState = rememberPagerState(pageCount = 3, initialPage = 0)
    Column() {
        MainTabs(pagerState = pagerState)
        MainTabsContent(pagerState = pagerState,navController)
    }
}

@ExperimentalPagerApi
@Composable
fun MainTabs(pagerState: PagerState) {
    val list = listOf(
        stringResource(id = R.string.preference_modes),
        stringResource(id = R.string.preference_tools),
        stringResource(id = R.string.preference_widget)
    )
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = dimensionResource(id = R.dimen.tab_indicator_height),
                color = MaterialTheme.colors.primary
            )
        }
    ) {
        list.forEachIndexed { index, _ ->
            Tab(
                text = { Text(text = list[index], style = preferenceTabFont)},
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

@ExperimentalPagerApi
@Composable
fun MainTabsContent(pagerState: PagerState,navController: NavController) {
    HorizontalPager(state = pagerState) { page ->
        when (page) {
            0 -> ModesTab(navController)
            1 -> ToolsTab()
            2 -> WidgetTab()
        }
    }
}