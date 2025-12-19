package alex.boruto.app.data.local.dao

import alex.boruto.app.domain.model.Hero
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HeroDao {

    @Query("SELECT * FROM hero_table ORDER BY id ASC")
    fun getAllHeroes(): PagingSource<Int, Hero>

    @Query("SELECT * FROM hero_table WHERE id = :heroId")
    fun getHeroById(heroId: Int): Hero

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHeroes(heroEntities: List<Hero>)

    @Query("DELETE FROM hero_table")
    suspend fun deleteAllHeroes()
}