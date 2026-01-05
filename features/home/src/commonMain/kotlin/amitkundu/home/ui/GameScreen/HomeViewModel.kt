package amitkundu.home.ui.GameScreen

import amitkundu.coreNetwork.util.NetworkResult
import amitkundu.home.domain.use_case.Get_Game_Use_Case.GetGameUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class HomeViewModel(
    private val useCase: GetGameUseCase
) : ViewModel(){

    private val _state = MutableStateFlow(GameScreenState())
    val state = _state.asStateFlow()

    init {
        getGames()
    }

    fun refresh() {
        _state.update { it.copy(isRefreshing = true) }
        getGames(false)

    }

    fun getGames(isLoadingShow: Boolean = true) {
        useCase.invoke()
            .onEach {result ->
            when(result){
                is NetworkResult.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isRefreshing= false,
                            error = result.message ?: "Error"
                        )
                    }
                }
                NetworkResult.Loading ->{
                    _state.update {
                        it.copy(
                            isLoading = true,
                        )
                    }
                }
                is NetworkResult.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isRefreshing = false,
                            error = "",
                            gameList = result.data.results
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

}