package alex.boruto.app.domain.use_cases.save_onboarding

import alex.boruto.app.data.repository.Repository

class SaveOnboardingUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(completed: Boolean) {
        repository.saveOnBoardingState(completed = completed)
    }
}