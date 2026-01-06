package amitkundu.home.ui.GameScreen

import amitkundu.theme.BackgroundDark
import amitkundu.theme.GameCard.GameCard
import amitkundu.theme.PrimaryBlue
import amitkundu.theme.RememberIsScrollingUp.rememberIsScrollingUp
import amitkundu.theme.SurfaceDark
import amitkundu.theme.TextPrimary
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.ktor.sse.COLON
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

        SubHomeScreen(
            state, onScrollChange = onScrollChange,
            onRefresh = {
                viewModel.refresh()
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class,
    ExperimentalAnimationApi::class)
@Composable
private fun SubHomeScreen(
    state: GameScreenState,
    onScrollChange: (Boolean) -> Unit,
    onRefresh: () -> Unit,
) {
    val listState = rememberLazyListState()
    val isScrollingUp = rememberIsScrollingUp(listState)


    LaunchedEffect(isScrollingUp) {
        onScrollChange(isScrollingUp)
    }
    val pullRefreshState = rememberPullToRefreshState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Game Hub",
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BackgroundDark,
                    titleContentColor = TextPrimary
                )
            )
        },
        containerColor = SurfaceDark
    )
    { paddingValues ->

        PullToRefreshBox(
            isRefreshing = state.isRefreshing,
            onRefresh = onRefresh,
            state = pullRefreshState,
            modifier = Modifier
                .fillMaxSize()
                .background(SurfaceDark)

        ) {

            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(top = 8.dp, bottom = 40.dp)
            )
            {
                if (state.error.isNotBlank()) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillParentMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = state.error,
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                } else {
                    items(
                        items = state.gameList,
                        key = { it.id } // IMPORTANT for animation
                    )
                    { game ->

                        AnimatedVisibility(
                            visible = true,
                            enter = fadeIn(
                                animationSpec = tween(600)
                            ) + slideInVertically(
                                animationSpec = tween(600),
                                initialOffsetY = { it / 3 }
                            )
                        ) {
                            Column {
                                GameCard(
                                    title = game.name,
                                    imageUrl = game.background_image ?: "",
                                    rating = game.rating.toFloat(),
                                    onlineCount = "${game.playtime} online",
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }
                    }
                }
            }


            if (state.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(PrimaryBlue.copy(alpha = 0.6f)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()

                }
            }
        }
    }
}

