package amitkundu.home.data.repoImpl

import amitkundu.home.data.Apis.GameApis
import amitkundu.home.data.model.game.GameResponse
import amitkundu.home.domain.repo.GameRepo

class GameRepoImpl (
    private val apis : GameApis
): GameRepo{
    override suspend fun getGames(): GameResponse  = apis.getGames()
}