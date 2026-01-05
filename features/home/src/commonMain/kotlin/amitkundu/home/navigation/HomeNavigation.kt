package amitkundu.home.navigation

import amitkundu.home.ui.GameScreen.HomeScreen
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation


fun NavGraphBuilder.homeNavigation(navController: NavHostController, onScrollChange: (Boolean) -> Unit){

    navigation<HomeRouts.HomeGraph>(startDestination = HomeRouts.HomeScreenRoute) {
        composable<HomeRouts.HomeScreenRoute> {
            HomeScreen(
                onScrollChange = onScrollChange
            )
        }
    }
}
