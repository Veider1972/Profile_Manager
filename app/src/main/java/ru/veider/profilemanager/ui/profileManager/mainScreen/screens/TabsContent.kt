package ru.veider.profilemanager.ui.profileManager.mainScreen.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.arttttt.nav3router.Router
import org.koin.android.ext.koin.androidContext
import org.koin.compose.KoinApplication
import ru.veider.profilemanager.data.dayProfile
import ru.veider.profilemanager.di.appModule
import ru.veider.profilemanager.di.gsonModule
import ru.veider.profilemanager.domain.ProfileTabItem
import ru.veider.profilemanager.domain.ProfileType
import ru.veider.profilemanager.domain.Screen
import ru.veider.profilemanager.domain.Widget

@Composable
fun TabsContent(tabs: List<ProfileTabItem>, pagerState: PagerState) {
	HorizontalPager(
		state = pagerState,
		verticalAlignment = Alignment.Top,
		modifier = Modifier.fillMaxWidth()) { page ->
		tabs[page].screen()
	}
}

@Preview
@Composable
fun TabsContentPreview() {
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
		TabsContent(tabs = tabs, pagerState = pagerState)
	}
}