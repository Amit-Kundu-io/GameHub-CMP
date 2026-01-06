package amitkundu.search.ui

import amitkundu.coreNetwork.util.NetworkResult
import amitkundu.search.domain.use_case.SearchUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update


class SearchViewModel(
    private val useCase: SearchUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SearchUiState())
    val state = _state.asStateFlow()


    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    init {
        observeSearch()
    }

    fun onEvent(event: SearchUiEvent) {
        when (event) {
            is SearchUiEvent.OnQueryChange -> {
                _query.value = event.q
            }
        }
    }

    @OptIn(FlowPreview::class)
    private fun observeSearch() {
        _query
            .debounce(400)
            .map { it.trim() }
            .distinctUntilChanged()
            .filter { it.length >= 3 }
            .onEach { onSearch(it) }
            .launchIn(viewModelScope)
    }



    private fun onSearch(q: String) {
        if (q.isBlank()) return

        useCase.invoke(q)
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