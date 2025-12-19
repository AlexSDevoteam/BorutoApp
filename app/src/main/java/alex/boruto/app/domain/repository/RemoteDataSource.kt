package alex.boruto.app.domain.repository

import alex.boruto.app.domain.model.Hero
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllHeroes(): Flow<PagingData<Hero>>
    fun searchHeroes(query: String): Flow<PagingData<Hero>>
}