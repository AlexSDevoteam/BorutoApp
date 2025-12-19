package alex.boruto.app.domain.model

import alex.boruto.app.util.Constants
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Constants.HERO_REMOTE_KEYS_DATABASE_TABLE)
data class HeroRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val previousPage: Int?,
    val nextPage: Int?,
    val lastUpdated : Long?
)