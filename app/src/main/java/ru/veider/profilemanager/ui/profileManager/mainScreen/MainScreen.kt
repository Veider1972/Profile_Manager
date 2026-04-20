package ru.veider.profilemanager.ui.profileManager.mainScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.*
import androidx.navigation3.ui.NavDisplay
import com.arttttt.nav3router.*
import kotlinx.coroutines.launch
import ru.veider.profilemanager.ui.profileManager.mainScreen.drawer.DrawerSheet
import ru.veider.profilemanager.ui.profileManager.mainScreen.screens.ProfilesMainScreen
import ru.veider.profilemanager.domain.Screen
import ru.veider.profilemanager.ui.profileManager.mainScreen.drawer.TopBar
import ru.veider.profilemanager.ui.profileManager.profileEditor.EditorMainState
import ru.veider.profilemanager.ui.profileManager.profileEditor.ProfileWidgetScreen

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {

    val scope = rememberCoroutineScope()

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val backStack = rememberNavBackStack(Screen.Profiles)
    val router = rememberRouter<Screen>()
    val navigator = rememberNav3Navigator(backStack) {}

    Scaffold(modifier = Modifier.fillMaxSize()) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                DrawerSheet(
                    state = backStack.last() as Screen,
                    onStateChange = {
                        scope.launch {
                            backStack.add(it)
                            drawerState.close()
                        }
                    }
                )
            }
        ) {
            Scaffold(
                topBar = {
                    TopBar(
                        onOpenDrawer = {
                            scope.launch {
                                drawerState.open()
                            }
                        },
                        onSettings = {},
                    )
                }
            ) { paddingValue ->
                Nav3Host(
                    backStack = backStack,
                    router = router,
                    navigator = navigator
                ) { backStack, onBack, router ->
                    NavDisplay(
                        backStack = backStack,
                        modifier = Modifier.padding(paddingValue),
                        onBack = {
                            router.pop()
                        },
                        entryProvider = entryProvider {
                            entry<Screen.Profiles> {
                                ProfilesMainScreen(router)
                            }
                            entry<Screen.ProfileEditor> {
                                EditorMainState(router, it.profile)
                            }
                        },
                    )
                }
            }
        }
    }
}