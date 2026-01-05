package amitkundu.search.domain.repo

import amitkundu.search.data.model.game.GameResponse

interface SearchRepo {
    suspend fun search(q : String) : GameResponse
}