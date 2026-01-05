package amitkundu.dashboard

import amitkundu.favorite.navigation.favoriteNavigation
import amitkundu.home.navigation.HomeRouts
import amitkundu.home.navigation.homeNavigation
import amitkundu.search.navigation.searchNavigation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun BottomNavHost(
    navController: NavHostController,
    onScrollChange: (Boolean) -> Unit
    ) {



    NavHost(
        navController = navController,
        startDestination = HomeRouts.HomeGraph,
        //modifier = modifier
    ) {
        homeNavigation(navController, onScrollChange = onScrollChange)
        favoriteNavigation(navController)
        searchNavigation(navController)
    }
}
