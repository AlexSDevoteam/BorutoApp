package alex.boruto.app.domain.repository

import alex.boruto.app.domain.model.Hero

interface LocalDataSource {
    suspend fun getSelectedHero(heroId: Int): Hero
}