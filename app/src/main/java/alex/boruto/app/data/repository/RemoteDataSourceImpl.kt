package alex.boruto.app.data.repository

import alex.boruto.app.data.local.BorutoDatabase
import alex.boruto.app.data.paging_source.HeroRemoteMediator
import alex.boruto.app.data.paging_source.SearchHeroesSource
import alex.boruto.app.data.remote.BorutoApi
import alex.boruto.app.domain.model.Hero
import alex.boruto.app.domain.repository.RemoteDataSource
import alex.boruto.app.util.Constants.ITEMS_PER_PAGE
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalPagingApi::class)
class RemoteDataSourceImpl(
    private val borutoApi: BorutoApi,
    private val borutoDatabase: BorutoDatabase
) : RemoteDataSource {
    override fun getAllHeroes(): Flow<PagingData<Hero>> {
        val pagingSourceFactory = { borutoDatabase.heroDao.getAllHeroes() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = HeroRemoteMediator(
                borutoApi = borutoApi,
                borutoDatabase = borutoDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchHeroes(query: String): Flow<PagingData<Hero>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchHeroesSource(borutoApi = borutoApi, query = query)
            }
        ).flow
    }
}