package alex.boruto.app.data.repository

import alex.boruto.app.data.local.BorutoDatabase
import alex.boruto.app.domain.model.Hero
import alex.boruto.app.domain.repository.LocalDataSource

class LocalDataSourceImpl(private val borutoDatabase: BorutoDatabase) : LocalDataSource {
    override suspend fun getSelectedHero(heroId: Int): Hero =
        borutoDatabase.heroDao.getHeroById(heroId)

}