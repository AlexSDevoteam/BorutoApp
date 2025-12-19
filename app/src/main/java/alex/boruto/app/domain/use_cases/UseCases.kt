package alex.boruto.app.domain.use_cases

import alex.boruto.app.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import alex.boruto.app.domain.use_cases.get_selected_hero.GetSelectedHeroUseCase
import alex.boruto.app.domain.use_cases.read_onboarding.ReadOnboardingUseCase
import alex.boruto.app.domain.use_cases.save_onboarding.SaveOnboardingUseCase
import alex.boruto.app.domain.use_cases.search_heroes.SearchHeroesUseCase

data class UseCases(
    val saveOnboardingUseCase: SaveOnboardingUseCase,
    val readOnboardingUseCase: ReadOnboardingUseCase,
    val getAllHeroesUseCase: GetAllHeroesUseCase,
    val searchHeroesUseCase: SearchHeroesUseCase,
    val getSelectedHeroUseCase: GetSelectedHeroUseCase
)