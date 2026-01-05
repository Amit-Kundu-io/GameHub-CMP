package amitkundu.home.domain.repo

import amitkundu.home.data.model.game.GameResponse

interface GameRepo {
    suspend fun getGames() : GameResponse
}