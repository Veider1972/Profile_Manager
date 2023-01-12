package ru.veider.profilemanager.ui.preference_activity

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import ru.veider.profilemanager.R
import ru.veider.profilemanager.ui.theme.preferenceTabFont

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabLayout() {
    val pagerState = rememberPagerState(pageCount = 3, initialPage = 0)
    Column() {
        ContactsTabs(pagerState = pagerState)
        ContactsTabsContent(pagerState = pagerState)
    }
}

@ExperimentalPagerApi
@Composable
fun ContactsTabs(pagerState: PagerState) {
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
                height = 2.dp
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
fun ContactsTabsContent(pagerState: PagerState) {
    HorizontalPager(state = pagerState) { page ->
        when (page) {
            0 -> ModesView()
            1 -> ToolsView()
            2 -> WidgetView()
        }
    }
}