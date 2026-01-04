package amitkundu.home.navigation

import amitkundu.home.ui.HomeScreen
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation


fun NavGraphBuilder.homeNavigation(navController: NavHostController,){

    navigation<HomeRouts.HomeGraph>(startDestination = HomeRouts.HomeScreenRoute) {
        composable<HomeRouts.HomeScreenRoute> {
            HomeScreen()
        }
    }
}
