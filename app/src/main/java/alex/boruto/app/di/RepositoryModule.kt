package alex.boruto.app.di

import alex.boruto.app.data.local.preferences.DataStoreOperationsImpl
import alex.boruto.app.data.repository.Repository
import alex.boruto.app.domain.repository.DataStoreOperations
import alex.boruto.app.domain.use_cases.UseCases
import alex.boruto.app.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import alex.boruto.app.domain.use_cases.get_selected_hero.GetSelectedHeroUseCase
import alex.boruto.app.domain.use_cases.read_onboarding.ReadOnboardingUseCase
import alex.boruto.app.domain.use_cases.save_onboarding.SaveOnboardingUseCase
import alex.boruto.app.domain.use_cases.search_heroes.SearchHeroesUseCase
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(@ApplicationContext context: Context): DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            saveOnboardingUseCase = SaveOnboardingUseCase(repository = repository),
            readOnboardingUseCase = ReadOnboardingUseCase(repository = repository),
            getAllHeroesUseCase = GetAllHeroesUseCase(repository = repository),
            searchHeroesUseCase = SearchHeroesUseCase(repository = repository),
            getSelectedHeroUseCase = GetSelectedHeroUseCase(repository = repository)
        )
    }
}