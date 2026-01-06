package amitkundu.search.ui

import amitkundu.theme.BackgroundDark
import amitkundu.theme.GameCard.GameCard
import amitkundu.theme.SurfaceDark
import amitkundu.theme.TextPrimary
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel()
){

    val state by viewModel.state.collectAsStateWithLifecycle()

    val q by viewModel.query.collectAsStateWithLifecycle()
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

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(vertical = 8.dp)
        ) {

            OutlinedTextField(
                value = q,
                onValueChange = {
                    viewModel.onEvent(SearchUiEvent.OnQueryChange(it))
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            viewModel.onEvent(SearchUiEvent.OnQueryChange(""))
                        }
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },

                placeholder = {
                    Text("Search Game")
                },
                modifier = Modifier.fillMaxWidth(),

                )


            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxSize(),
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


                if (state.isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(BackgroundDark.copy(alpha = 0.6f)),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()

                    }
                }
            }


        }
    }
}