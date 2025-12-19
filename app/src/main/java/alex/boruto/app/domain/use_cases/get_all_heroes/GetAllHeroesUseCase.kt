package alex.boruto.app.domain.use_cases.get_all_heroes

import alex.boruto.app.data.repository.Repository

class GetAllHeroesUseCase(
    private val repository: Repository
) {
    operator fun invoke() = repository.getAllHeroes()
}