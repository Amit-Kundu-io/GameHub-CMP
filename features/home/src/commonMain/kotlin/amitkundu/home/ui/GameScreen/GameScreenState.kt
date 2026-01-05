package amitkundu.home.ui.GameScreen

import amitkundu.home.data.model.game.Game
import amitkundu.home.data.model.game.GameResponse

data class GameScreenState (
    val isLoading : Boolean = false,
    val error : String = "",
    val gameList : List<Game> = emptyList()
)