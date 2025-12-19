package alex.boruto.app.data.local.dao

import alex.boruto.app.domain.model.HeroRemoteKeys
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HeroRemoteKeyDao {

    @Query("SELECT * FROM hero_remote_keys_table WHERE id = :heroId")
    suspend fun getRemoteKeys(heroId: Int): HeroRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(heroRemoteKeyEntities: List<HeroRemoteKeys>)

    @Query("DELETE FROM hero_remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}