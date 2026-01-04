package amitkundu.search.navigation

import amitkundu.search.ui.SearchScreen
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation


fun NavGraphBuilder.searchNavigation(navController: NavHostController,){
    navigation<SearchRouts.SearchGraph>(startDestination = SearchRouts.SearchScreenRoute) {
        composable<SearchRouts.SearchScreenRoute> {
            SearchScreen()
        }
    }
}