package alex.boruto.app.data.repository

import alex.boruto.app.domain.model.Hero
import alex.boruto.app.domain.repository.DataStoreOperations
import alex.boruto.app.domain.repository.LocalDataSource
import alex.boruto.app.domain.repository.RemoteDataSource
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val dataStore: DataStoreOperations,
    private val localDataSource: LocalDataSource
) {
    fun getAllHeroes(): Flow<PagingData<Hero>> {
        return remoteDataSource.getAllHeroes()
    }

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed = completed)
    }

    suspend fun readOnBoardingState() = dataStore.readOnBoardingState()

    fun searchHeroes(query: String): Flow<PagingData<Hero>> =
        remoteDataSource.searchHeroes(query = query)

    suspend fun getSelectedHero(heroId: Int): Hero = localDataSource.getSelectedHero(heroId)

}