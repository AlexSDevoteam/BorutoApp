package alex.boruto.app.domain.use_cases.search_heroes

import alex.boruto.app.data.repository.Repository
import alex.boruto.app.domain.model.Hero
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class SearchHeroesUseCase(
    private val repository: Repository
) {
    operator fun invoke(query: String): Flow<PagingData<Hero>> {
        return repository.searchHeroes(query = query)
    }
}