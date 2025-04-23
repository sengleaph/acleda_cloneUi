import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.sifu.trainingui.R
import com.sifu.trainingui.components.utils.navigation_bar.Screen

data class NavigationItem(
    val title: String,
    val selectedIcon: Painter,
    val unSelectedIcon: Painter,
    val route: String
)

@Composable
fun navigationItems(): List<NavigationItem> {
    return listOf(
        NavigationItem(
            title = "Home",
            selectedIcon = painterResource(id = R.drawable.ic_home_filled),
            unSelectedIcon = painterResource(id = R.drawable.ic_home_outline),
            route = Screen.Home.route
        ),
        NavigationItem(
            title = "Favorites",
            selectedIcon = painterResource(id = R.drawable.ic_favorite_filled),
            unSelectedIcon = painterResource(id = R.drawable.ic_favorite_outline),
            route = Screen.Favorite.route
        ),
        NavigationItem(
            title = "Notifications",
            selectedIcon = painterResource(id = R.drawable.ic_notification_filled),
            unSelectedIcon = painterResource(id = R.drawable.ic_notification_outline),
            route = Screen.Notification.route
        ),
        NavigationItem(
            title = "Setting",
            selectedIcon = painterResource(id = R.drawable.ic_menu_image),
            unSelectedIcon = painterResource(id = R.drawable.ic_menu_image),
            route = Screen.Menu.route
        )
    )
}

