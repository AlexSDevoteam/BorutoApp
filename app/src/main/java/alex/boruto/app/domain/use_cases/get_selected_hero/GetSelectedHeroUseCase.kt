package alex.boruto.app.domain.use_cases.get_selected_hero

import alex.boruto.app.data.repository.Repository

class GetSelectedHeroUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(heroId: Int) = repository.getSelectedHero(heroId)
}