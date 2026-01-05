package amitkundu.search.data.repo_impl

import amitkundu.search.data.apis.SearchApis
import amitkundu.search.data.model.game.GameResponse
import amitkundu.search.domain.repo.SearchRepo

class SearchRepoImpl (
    private val apis: SearchApis
): SearchRepo{
    override suspend fun search(q: String): GameResponse {
        return apis.search(q)
    }
}