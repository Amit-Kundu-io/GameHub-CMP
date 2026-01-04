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
    ) {



    NavHost(
        navController = navController,
        startDestination = HomeRouts.HomeGraph,
        //modifier = modifier
    ) {
        homeNavigation(navController)
        favoriteNavigation(navController)
        searchNavigation(navController)
    }
}
