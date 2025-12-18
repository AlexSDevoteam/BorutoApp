package alex.boruto.app.domain.model

import alex.boruto.app.util.Constants.HERO_REMOTE_KEY_DATABASE_TABLE
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = HERO_REMOTE_KEY_DATABASE_TABLE)
data class HeroRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val previousPage: Int?,
    val nextPage: Int?
)
