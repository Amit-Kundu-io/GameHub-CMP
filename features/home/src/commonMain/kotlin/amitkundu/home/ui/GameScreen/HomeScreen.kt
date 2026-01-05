package amitkundu.home.ui.GameScreen

import amitkundu.theme.RememberIsScrollingUp.rememberIsScrollingUp
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun HomeScreen(
    onScrollChange: (Boolean) -> Unit,
    viewModel: HomeViewModel = koinViewModel()
){
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        SubHomeScreen(state, onScrollChange = onScrollChange)


    }
}

@OptIn(ExperimentalMaterial3Api::class,
    ExperimentalAnimationApi::class)
@Composable
private fun SubHomeScreen(state: GameScreenState, onScrollChange: (Boolean) -> Unit) {
    val listState = rememberLazyListState()
    val isScrollingUp = rememberIsScrollingUp(listState)


    LaunchedEffect(isScrollingUp) {
        onScrollChange(isScrollingUp)
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Game Hub")
                }
            )
        }
    ) { paddingValues ->

        AnimatedContent(
            targetState = Triple(state.isLoading, state.error, state.gameList),
            transitionSpec = {
                fadeIn(animationSpec = tween(400)) with
                        fadeOut(animationSpec = tween(300))
            },
            label = "home_state_animation"
        ) { (isLoading, error, games) ->

            when {
                isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                error.isNotBlank() -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = error,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }

                else -> {
                    LazyColumn(
                        state = listState,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        items(
                            items = games,
                            key = { it.id } // IMPORTANT for animation
                        ) { game ->

                            AnimatedVisibility(
                                visible = true,
                                enter = fadeIn(
                                    animationSpec = tween(600)
                                ) + slideInVertically(
                                    animationSpec = tween(600),
                                    initialOffsetY = { it / 3 }
                                )
                            ) {
                                GameCard(
                                    image = game.background_image ?: "",
                                    title = game.name
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

