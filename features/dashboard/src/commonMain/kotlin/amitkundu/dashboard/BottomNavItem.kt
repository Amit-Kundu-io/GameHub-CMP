package amitkundu.dashboard

import amitkundu.favorite.navigation.FavoriteRouts
import amitkundu.home.navigation.HomeRouts
import amitkundu.search.navigation.SearchRouts
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector


sealed class BottomNavItem(
    val route: String?,
    val label: String,
    val selectIcon: ImageVector,
    val unselectIcon: ImageVector
) {

    data object Home : BottomNavItem(
        route = HomeRouts.HomeScreenRoute::class.qualifiedName,
        label = "Home",
        selectIcon = Icons.Filled.Home,
        unselectIcon = Icons.Outlined.Home
    )

    data object Favorite : BottomNavItem(
        route = FavoriteRouts.FavoriteScreenRoute::class.qualifiedName,
        label = "Favorite",
        selectIcon = Icons.Filled.Favorite,
        unselectIcon = Icons.Outlined.Favorite
    )

    data object Search : BottomNavItem(
        route = SearchRouts.SearchScreenRoute::class.qualifiedName,
        label = "Search",
        selectIcon = Icons.Filled.Search,
        unselectIcon = Icons.Outlined.Search
    )


}
val bottomNavItems = listOf(
    BottomNavItem.Home,
    BottomNavItem.Search,
    BottomNavItem.Favorite,
)


// To detect when to show bottom nav
val bottomNavRoutes = bottomNavItems.map { it.route }.toSet()
