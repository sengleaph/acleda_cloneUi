package com.sifu.trainingui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.sifu.trainingui.components.utils.navigation_bar.ButtonNavigationBar
import com.sifu.trainingui.components.utils.navigation_bar.NavGraph
import com.sifu.trainingui.components.utils.navigation_bar.NavigationBarQrCodeScanner
import com.sifu.trainingui.components.utils.navigation_bar.Screen
import com.sifu.trainingui.ui.theme.TrainingUITheme
import navigationItems

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrainingUITheme {
                Surface {
                    val navController = rememberNavController()
                    val backStack by navController.currentBackStackEntryAsState()
                    val currentRoute = backStack?.destination?.route

                    // Scroll‑up detection drives bottom bar visibility
                    var isScrollingUp by remember { mutableStateOf(true) }
                    val nestedScroll = remember {
                        object : NestedScrollConnection {
                            override fun onPreScroll(
                                available: Offset,
                                source: NestedScrollSource
                            ): Offset {
                                isScrollingUp = available.y >= 0f
                                return Offset.Zero
                            }
                        }
                    }

                    // Root Box acts like Flutter's Stack
                    Box(modifier = Modifier.fillMaxSize()) {
                        // 1) The main Scaffold without bottomBar slot
                        Scaffold(
                            modifier = Modifier.nestedScroll(nestedScroll),
                            floatingActionButton = {
                                // Your FAB; you can animate this separately if desired
                                if (currentRoute in navigationItems().map { it.route }) {
                                    AnimatedVisibility(
                                        visible = !isScrollingUp,
                                        enter   = slideInVertically(
                                            initialOffsetY = { it },
                                            animationSpec = tween(600, easing = FastOutSlowInEasing)
                                        ) + fadeIn(tween(300)),
                                        exit    = slideOutVertically(
                                            targetOffsetY = { it },
                                            animationSpec = tween(600, easing = FastOutSlowInEasing)
                                        ) + fadeOut(tween(300))
                                    )  {
                                        NavigationBarQrCodeScanner {
                                            navController.navigate(Screen.KHQR.route)
                                        }
                                    }
                                }
                            },
                            floatingActionButtonPosition = FabPosition.Center,
                            content = { innerPadding ->
                                // Ensure your NavGraph content gets proper padding & fills
                                NavGraph(
                                    navController  = navController,
                                    modifier       = Modifier
                                        .padding(innerPadding)
                                        .fillMaxSize()
                                )
                            }
                        )

                        // 2) Overlayed bottom bar, animated in/out on scroll
                        if (currentRoute in navigationItems().map { it.route }) {
                            AnimatedVisibility(
                                visible = isScrollingUp,
                                enter = slideInVertically(
                                    initialOffsetY = { it },
                                    animationSpec  = tween(300, easing = FastOutSlowInEasing)
                                ) + fadeIn(tween(300)),
                                exit = slideOutVertically(
                                    targetOffsetY = { it },
                                    animationSpec = tween(300, easing = FastOutSlowInEasing)
                                ) + fadeOut(tween(300)),
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .fillMaxWidth()
                            ) {
                                val nav = currentRoute ?: navigationItems().first().route
                                ButtonNavigationBar(
                                    items          = navigationItems(),
                                    currentRoute   = nav,
                                    onItemSelected = { navController.navigate(it.route) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
