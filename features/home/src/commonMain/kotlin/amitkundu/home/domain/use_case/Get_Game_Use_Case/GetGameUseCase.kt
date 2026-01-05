package amitkundu.home.domain.use_case.Get_Game_Use_Case

import amitkundu.coreNetwork.util.NetworkResult
import amitkundu.home.data.model.game.GameResponse
import amitkundu.home.domain.repo.GameRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetGameUseCase (
    private val repo: GameRepo
) {
    operator fun invoke() : Flow<NetworkResult<GameResponse>> = flow {

        try {
            val data = repo.getGames()
            emit(NetworkResult.Success(data))
        }
        catch (e : Exception){
            emit(NetworkResult.Error(e.message))
        }
    }
        .flowOn(Dispatchers.IO)
}