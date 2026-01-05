package amitkundu.search.domain.use_case

import amitkundu.coreNetwork.util.NetworkResult
import amitkundu.search.data.model.game.GameResponse
import amitkundu.search.domain.repo.SearchRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.io.IOException

class SearchUseCase(
    private val repo: SearchRepo
) {
    operator fun invoke(q: String): Flow<NetworkResult<GameResponse>> = flow {
        emit(NetworkResult.Loading)
        try {
            val data = repo.search(q = q)
            emit(NetworkResult.Success(data))

        }
        catch (e: IOException) {
            emit(NetworkResult.Error("Check your internet connection"))

        }
        catch (e: Exception) {
            emit(NetworkResult.Error(e.message))
        }
    }
}