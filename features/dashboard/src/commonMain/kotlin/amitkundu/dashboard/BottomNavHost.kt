package amitkundu.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun BottomNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    navigateToLoginScreen: () -> Unit,
    startingRoute: String,

    ) {


    val starting = when (startingRoute) {
        FileFrogUtils.HOME -> HomeScreenRoute
        FileFrogUtils.TEAM -> TeamGraph
        FileFrogUtils.CLIENTS -> ClientGraph
        FileFrogUtils.TASKS -> MyTaskGraph
        FileFrogUtils.SETTINGS -> SettingGraph
        else -> HomeScreenRoute
    }


    NavHost(
        navController = navController,
        startDestination = starting,
        //modifier = modifier
    ) {
        homeNavigation(
            navController,
            onBusinessProfileClick = { navController.navigate(MyBusinessRoute) },
            navigateToProfile = {
                navController.navigate(PersonalProfileRoute)
            }
        )
        teamNavigation(
            navController,
            onBusinessProfileClick = { navController.navigate(MyBusinessRoute) },
            navigateToProfile = {
                navController.navigate(PersonalProfileRoute)
            }
        )

        clientsNavigation(
            navController,
            onBusinessProfileClick = {
                navController.navigate(MyBusinessRoute)
            },
            navigateToProfile = {
                navController.navigate(PersonalProfileRoute)
            }
        )
        myTaskNavigation(
            navController,
            onBusinessProfileClick = {
                navController.navigate(MyBusinessRoute)
            },
            navigateToProfile = {
                navController.navigate(PersonalProfileRoute)
            })
        settingNavigation(
            navController,
            navigateToLoginScreen = navigateToLoginScreen,
            onBusinessProfileClick = { navController.navigate(MyBusinessRoute) },
            navigateToProfile = {
                navController.navigate(PersonalProfileRoute)
            }
        )
    }
}
