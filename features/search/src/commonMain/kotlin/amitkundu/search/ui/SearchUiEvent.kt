package amitkundu.search.ui

sealed class SearchUiEvent {

    data class OnQueryChange(val q : String) : SearchUiEvent()
}