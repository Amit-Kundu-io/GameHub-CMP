package amitkundu.favorite.ui


import amitkundu.theme.BackgroundDark
import amitkundu.theme.GameCard.GameCard
import amitkundu.theme.SurfaceDark
import amitkundu.theme.TextPrimary
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {

                Lifecycle.Event.ON_CREATE -> {

                }

                Lifecycle.Event.ON_START -> {
                    viewModel.getData()
                }

                Lifecycle.Event.ON_RESUME -> {

                }

                else -> Unit
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    SubFavoriteScreen(
        state,
        onDelete = {
            viewModel.deleteGame(it)
        }
    )

}


@OptIn(ExperimentalMaterial3Api::class,
    ExperimentalAnimationApi::class)
@Composable
private fun SubFavoriteScreen(
    state: FavoriteState,
    onDelete: (Int) -> Unit
) {
    val listState = rememberLazyListState()


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

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(SurfaceDark)
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {

            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(top = 8.dp, bottom = 50.dp)
            ) {

                if (state.isLoading) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillParentMaxHeight()
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                } else {
                    val games = state.data.orEmpty()
                    items(
                        items = games,
                        key = { it.id }
                    ) { game ->
                        GameCard(
                            title = game.name,
                            imageUrl = game.background_image ?: "",
                            rating = game.rating.toFloat(),
                            onlineCount = "${game.playtime} online",
                            showFavorite = true,
                            isFavorite = true,
                            onFavoriteClick = {
                                onDelete(game.id)
                            }
                        )
                    }
                }
            }

        }
    }
}
