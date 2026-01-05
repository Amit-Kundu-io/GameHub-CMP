package amitkundu.search.data.model.game

import kotlinx.serialization.Serializable

@Serializable
data class GameResponse(
    val results: List<Game>,
)