package amitkundu.dashboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun DashboardScreen() {
    val bottomNavController = rememberNavController()
    val currentRoute = bottomNavController.currentBackStackEntryAsState().value?.destination?.route

    val showBottomBar = bottomNavRoutes.any { route ->
        currentRoute?.contains(route ?: "", ignoreCase = true) == true
    }

//    var isShowBottomNav by remember {
//        mutableStateOf(true)
//    }


    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = showBottomBar,
                enter = fadeIn() + slideInVertically(
                    initialOffsetY = {
                        it / 2
                    }
                ),
                exit = fadeOut() + slideOutVertically(
                    targetOffsetY = {
                        it / 2
                    }
                )
            ) {

                NavigationBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 0.5.dp,
                            color = Color.LightGray,
                            shape = RectangleShape
                        ),
                    containerColor = Color.White
                ) {
                    bottomNavItems.forEach { item ->
                        NavigationBarItem(
                            selected = currentRoute == item.route,
                            onClick = {
                                bottomNavController.navigate(item.route ?: "") {
                                    popUpTo(bottomNavController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (currentRoute == item.route) item.selectIcon else item.unselectIcon,
                                    tint = Color.Unspecified,
                                    contentDescription = null
                                )
                            },
                            label = {
                                Text(
                                    item.label,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontSize = 11.sp,
                                        fontWeight = if (currentRoute == item.route) FontWeight.SemiBold else FontWeight.Medium,
                                        color = if (currentRoute == item.route) Color.Black else Color.Gray
                                    )
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.Transparent
                            )
                        )
                    }
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .then(
                    if (showBottomBar) {
                        Modifier.padding(bottom = 40.dp)
                    } else {
                        Modifier
                    }
                )
        ) {
            BottomNavHost(navController = bottomNavController)
        }
    }
}