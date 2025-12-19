package alex.boruto.app.di

import alex.boruto.app.data.local.BorutoDatabase
import alex.boruto.app.data.repository.LocalDataSourceImpl
import alex.boruto.app.domain.repository.LocalDataSource
import alex.boruto.app.util.Constants.BORUTO_DATABASE
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context = context,
        klass = BorutoDatabase::class.java,
        name = BORUTO_DATABASE
    ).build()

    @Provides
    @Singleton
    fun provideLocalDataSource(database: BorutoDatabase): LocalDataSource {
        return LocalDataSourceImpl(borutoDatabase = database)
    }
}