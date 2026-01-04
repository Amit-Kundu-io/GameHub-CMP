package amitkundu.favorite.navigation

import amitkundu.favorite.ui.FavoriteScreen
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation


fun NavGraphBuilder.favoriteNavigation(
    navController: NavHostController,

    ) {

    navigation<FavoriteRouts.FavoriteGraph>(startDestination = FavoriteRouts.FavoriteScreenRoute) {
        composable<FavoriteRouts.FavoriteScreenRoute> {
            FavoriteScreen()
        }
    }

}