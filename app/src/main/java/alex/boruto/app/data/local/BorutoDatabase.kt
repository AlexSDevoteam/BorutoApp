package alex.boruto.app.data.local

import alex.boruto.app.data.local.dao.HeroDao
import alex.boruto.app.data.local.dao.HeroRemoteKeyDao
import alex.boruto.app.domain.model.Hero
import alex.boruto.app.domain.model.HeroRemoteKey
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Hero::class, HeroRemoteKey::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class BorutoDatabase : RoomDatabase() {
    abstract val heroDao: HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao
}