//package com.sifu.trainingui.components.utils.navigation_bar
//
//import androidx.compose.animation.AnimatedVisibility
//import androidx.compose.animation.core.FastOutLinearInEasing
//import androidx.compose.animation.core.FastOutSlowInEasing
//import androidx.compose.animation.core.tween
//import androidx.compose.animation.slideInVertically
//import androidx.compose.animation.slideOutVertically
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.heightIn
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.FabPosition
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
//import androidx.compose.ui.input.nestedscroll.NestedScrollSource
//import androidx.compose.ui.input.nestedscroll.nestedScroll
//import androidx.compose.ui.unit.dp
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import androidx.navigation.createGraph
//import com.sifu.trainingui.Views.FavoritesPage.FavoritesScreen
//import com.sifu.trainingui.Views.HomePage.HomePage
//import com.sifu.trainingui.Views.MenusPage.MenuScreen
//import com.sifu.trainingui.Views.NotificationsPage.NotificationScreen
//import com.sifu.trainingui.ui.theme.ColorTransparent
//import com.sifu.trainingui.ui.theme.WhiteTransparent40
//import navigationItems
//
//@Composable
//fun MainScreen() {
//
//    val navController = rememberNavController()
//
//    val currentRoute = navController
//        .currentBackStackEntry
//        ?.destination
//        ?.route
//        ?: navigationItems()
//            .first()
//            .route
//    var isScrollingUp by remember { mutableStateOf(true) }
//
//    val nestedScrollConnection = remember {
//        object : NestedScrollConnection {
//            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
//               isScrollingUp = available.y > -0f
//                return Offset.Zero
//            }
//        }
//    }
//
//    Scaffold(
//        modifier = Modifier.nestedScroll(nestedScrollConnection),
////        bottomBar = {
////            ButtonNavigationBar(
////                items = navigationItems(),
////                currentRoute = navController.currentBackStackEntry
////                    ?.destination?.route
////                    ?: navigationItems().first().route,
////                onItemSelected = { navController.navigate(it.route) }
////            )
////        },
//        bottomBar = {
//            AnimatedVisibility(
//                visible = isScrollingUp,
//                enter   =  slideInVertically(
//                    initialOffsetY = { it },
//                    animationSpec  = tween(300, easing = FastOutSlowInEasing,  delayMillis = 300)
//                ),
//                exit = slideOutVertically(
//                    targetOffsetY = { it },
//                    animationSpec = tween(300, easing = FastOutSlowInEasing)
//                )
//            ) {
//                ButtonNavigationBar(
//                    items = navigationItems(),
//                    currentRoute = navController.currentBackStackEntry
//                        ?.destination?.route
//                        ?: navigationItems().first().route,
//                    onItemSelected = { navController.navigate(it.route) }
//                )
//            }
//        },
//        floatingActionButton = {
//            AnimatedVisibility(
//                visible = !isScrollingUp,
//                enter = slideInVertically (
//                    initialOffsetY = { it },
//                    animationSpec  = tween(600, easing = FastOutSlowInEasing, delayMillis = 300)
//                ),
//                exit = slideOutVertically(
//                    targetOffsetY = { it },
//                    animationSpec = tween(600, easing = FastOutSlowInEasing)
//                ),
//            ) {
//                NavigationBarQrCodeScanner(
//                    onClick = { /* handle click */ }
//                )
//            }
//        },
//        floatingActionButtonPosition = FabPosition.Center,
//    ){
//            innerPadding ->
//        NavHost(
//            navController = navController,
//            startDestination = Screen.Home.route,
//            modifier = Modifier.padding(innerPadding)
//        ){
//            composable(Screen.Home.route){
//                HomePage()
//            }
//            composable(Screen.Favorite.route){
//                FavoritesScreen()
//            }
//            composable(Screen.Notification.route){
//                NotificationScreen()
//            }
//            composable(Screen.Menu.route){
//                MenuScreen()
//            }
//        }
//    }
//}