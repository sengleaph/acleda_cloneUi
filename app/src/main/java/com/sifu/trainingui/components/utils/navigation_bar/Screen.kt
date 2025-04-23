package com.sifu.trainingui.components.utils.navigation_bar

sealed class Screen(val route: String) {
    data object Home: Screen("Home_Screen")
    data object Favorite: Screen("Favorites_Screen")
    data object Notification: Screen("Notification_Screen")
    data object Menu: Screen("Menu_Screen")
    data object Transfer: Screen("Transfer_Screen")
    data object KHQR: Screen("KHQR_Screen")
    data object ConfirmTransfer: Screen("ConfirmTransfer_Screen")
    data object SuccessTransfer: Screen("TransferSuccess_Screen")
}

