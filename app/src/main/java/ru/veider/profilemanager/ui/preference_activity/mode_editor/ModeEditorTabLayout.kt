package ru.veider.profilemanager.ui.preference_activity.mode_editor

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
import ru.veider.profilemanager.ui.preference_activity.mode_editor.tabs.ConnectionsTab
import ru.veider.profilemanager.ui.preference_activity.mode_editor.tabs.InfoTab
import ru.veider.profilemanager.ui.preference_activity.mode_editor.tabs.OtherTab
import ru.veider.profilemanager.ui.preference_activity.mode_editor.tabs.SoundsTab
import ru.veider.profilemanager.ui.theme.preferenceTabFont

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ModeEditorTabLayout(navController: NavController) {
    val pagerState = rememberPagerState(pageCount = 4, initialPage = 0)
    Column() {
        ModeEditorTabs(pagerState = pagerState)
        ModeEditorTabsContent(pagerState = pagerState, navController = navController)
    }
}

@ExperimentalPagerApi
@Composable
fun ModeEditorTabs(pagerState: PagerState) {
    val list = listOf(
        stringResource(id = R.string.mode_editor_info_tab_name),
        stringResource(id = R.string.mode_editor_sounds_tab_name),
        stringResource(id = R.string.mode_editor_connections_tab_name),
        stringResource(id = R.string.mode_editor_other_tab_name)
    )
    val scope = rememberCoroutineScope()
    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = dimensionResource(id = R.dimen.tab_indicator_height),
                color = MaterialTheme.colors.primary
            )
        },
        edgePadding = 0.dp
    ) {
        list.forEachIndexed { index, _ ->
            Tab(
                text = { Text(text = list[index], style = preferenceTabFont) },
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
fun ModeEditorTabsContent(pagerState: PagerState, navController: NavController) {
    HorizontalPager(state = pagerState) { page ->
        when (page) {
            0 -> InfoTab(navController = navController)
            1 -> SoundsTab(navController = navController)
            2 -> ConnectionsTab(navController = navController)
            3 -> OtherTab(navController = navController)
        }
    }
}