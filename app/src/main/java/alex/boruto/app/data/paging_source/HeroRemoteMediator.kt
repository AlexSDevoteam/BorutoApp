package alex.boruto.app.data.paging_source

import alex.boruto.app.data.local.BorutoDatabase
import alex.boruto.app.data.remote.BorutoApi
import alex.boruto.app.domain.model.Hero
import alex.boruto.app.domain.model.HeroRemoteKeys
import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class HeroRemoteMediator @Inject constructor(
    private val borutoApi: BorutoApi,
    private val borutoDatabase: BorutoDatabase
) : RemoteMediator<Int, Hero>() {
    val heroDao = borutoDatabase.heroDao
    val heroRemoteKeysDao = borutoDatabase.heroRemoteKeyDao

    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = heroRemoteKeysDao.getRemoteKeys(heroId = 1)?.lastUpdated
            ?: 0L
        val cacheTimeout = 720

        val differenceInMinutes = (currentTime - lastUpdated) / 1000 / 60
        return if (differenceInMinutes.toInt() <= cacheTimeout) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Hero>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val previousPage = remoteKeys?.previousPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    previousPage
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = borutoApi.getAllHeroes(page = page)
            val heroes = response.heroes
            val endOfPaginationReached = heroes.isEmpty()

            borutoDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    heroDao.deleteAllHeroes()
                    heroRemoteKeysDao.deleteAllRemoteKeys()
                }
                val prevPage = if (page == 1) null else page - 1
                val nextPage = if (endOfPaginationReached) null else page + 1
                val keys = heroes.map { hero ->
                    HeroRemoteKeys(
                        id = hero.id,
                        previousPage = prevPage,
                        nextPage = nextPage,
                        lastUpdated = response.lastUpdated
                    )
                }
                heroRemoteKeysDao.addAllRemoteKeys(keys)
                heroDao.addHeroes(heroes)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            Log.d("HeroRemoteMediator", "load: $e")
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Hero>
    ): HeroRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                heroRemoteKeysDao.getRemoteKeys(heroId = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Hero>
    ): HeroRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { hero ->
                heroRemoteKeysDao.getRemoteKeys(heroId = hero.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Hero>
    ): HeroRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { hero ->
                heroRemoteKeysDao.getRemoteKeys(heroId = hero.id)
            }
    }
}