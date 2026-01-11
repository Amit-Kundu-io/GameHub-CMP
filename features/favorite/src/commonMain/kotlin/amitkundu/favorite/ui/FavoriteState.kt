package amitkundu.favorite.ui

import amitkundu.database.GameEntity

data class FavoriteState (
    val isLoading : Boolean = false,
    val error : String = "",
    val data : List<GameEntity> = emptyList()
)