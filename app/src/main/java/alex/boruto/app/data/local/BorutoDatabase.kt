package alex.boruto.app.data.local

import alex.boruto.app.data.local.dao.HeroDao
import alex.boruto.app.data.local.dao.HeroRemoteKeyDao
import alex.boruto.app.domain.model.Hero
import alex.boruto.app.domain.model.HeroRemoteKeys
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Hero::class, HeroRemoteKeys::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class BorutoDatabase : RoomDatabase() {

    companion object {
        fun create(context: Context, useInMemory: Boolean): BorutoDatabase {
            val databaseBuilder = if (useInMemory) {
                Room.inMemoryDatabaseBuilder(context, BorutoDatabase::class.java)
            } else {
                Room.databaseBuilder(context, BorutoDatabase::class.java, "test_database.db")
            }
            return databaseBuilder
                .fallbackToDestructiveMigration(false)
                .build()
        }
    }

    abstract val heroDao: HeroDao
    abstract val heroRemoteKeyDao: HeroRemoteKeyDao
}