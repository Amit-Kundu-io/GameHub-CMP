package amitkundu.search.data.apis

import amitkundu.search.data.model.game.GameResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class SearchApis (
    private val  httpClient: HttpClient
) {
    suspend fun search(q : String) : GameResponse {
        return httpClient.get ("api/games"){
                url{
                    parameter("key",AppConfig.GAME_API_KEY)
                    parameter("search",q)
                }
            }.body<GameResponse>()

    }
}