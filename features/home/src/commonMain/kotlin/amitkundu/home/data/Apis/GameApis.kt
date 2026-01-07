package amitkundu.home.data.Apis

import amitkundu.home.data.model.game.GameResponse
import amitkundu.theme.AppConfig.AppConfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter


class GameApis (
        val httpClient: HttpClient
    ) {
    suspend fun getGames(): GameResponse {
        return httpClient.get("api/games") {
                    url{
                        parameter("key", AppConfig.GAME_API_KEY)
                    }
                }.body<GameResponse>()
        }

}