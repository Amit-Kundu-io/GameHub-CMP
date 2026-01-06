package amitkundu.search.ui

import amitkundu.search.data.model.game.Game

data class SearchUiState (
    val isLoading : Boolean = false,
    val isRefreshing: Boolean = false,
    val error : String = "",
    val gameList : List<Game> = emptyList(),
    val q : String = ""
)