package com.sifu.trainingui.components.utils.navigation_bar

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sifu.trainingui.Views.FavoritesPage.FavoritesScreen
import com.sifu.trainingui.Views.HomePage.HomePage
import com.sifu.trainingui.Views.MenusPage.MenuScreen
import com.sifu.trainingui.Views.NotificationsPage.NotificationScreen
import com.sifu.trainingui.Views.QrCodeBakong.KHQRScreen
import com.sifu.trainingui.Views.TransferPage.TransferScreen
import com.sifu.trainingui.Views.TransferPage.widget.ConfirmTransferScreen
import com.sifu.trainingui.Views.TransferPage.widget.SuccessTransferScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = Screen.Home.route
) {
    NavHost(
        navController    = navController,
        startDestination = startDestination,
        modifier         = modifier
    ) {
        composable(
            Screen.Home.route,
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(600))
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(600))
            }
        ) {
            HomePage(navController)
        }
        composable(Screen.Favorite.route) {
            FavoritesScreen(navController)
        }
        composable(Screen.Notification.route) {
            NotificationScreen(navController)
        }
        composable(Screen.Menu.route) {
            MenuScreen(navController)
        }
        composable(Screen.Transfer.route) {
            TransferScreen(navController)
        }
        composable(
            Screen.KHQR.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(600))
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(1200))
            }
        ) {
            KHQRScreen(navController)
        }
        composable(
            Screen.ConfirmTransfer.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(600))
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(1200))
            }
        ) {
            ConfirmTransferScreen(
                navController
            )
        }
        composable(
            Screen.SuccessTransfer.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(600))
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(1200))
            }
        ){
            SuccessTransferScreen(
                navController = navController
            )
        }
        }
    }

