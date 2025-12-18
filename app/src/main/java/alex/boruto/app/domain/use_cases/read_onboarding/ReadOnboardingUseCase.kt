package alex.boruto.app.domain.use_cases.read_onboarding

import alex.boruto.app.data.repository.Repository

class ReadOnboardingUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke() = repository.readOnBoardingState()
}