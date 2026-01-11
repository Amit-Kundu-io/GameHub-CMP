package amitkundu.favorite.ui

import amitkundu.database.GameDao
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoriteViewModel (
    private val dao: GameDao
) : ViewModel(){
    private val _state = MutableStateFlow(FavoriteState())
    val state = _state.asStateFlow()

    fun getData() {
        viewModelScope.launch {
            dao.getAllGames()
                .onStart {
                    _state.update { it.copy(isLoading = true) }
                }
                .collectLatest { res ->
                    _state.update {
                        it.copy(
                            data = res,
                            isLoading = false
                        )
                    }
                }
        }
    }


}