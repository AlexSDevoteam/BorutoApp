package alex.boruto.app.domain.use_cases

import alex.boruto.app.domain.use_cases.read_onboarding.ReadOnboardingUseCase
import alex.boruto.app.domain.use_cases.save_onboarding.SaveOnboardingUseCase

data class UseCases(
    val saveOnboardingUseCase: SaveOnboardingUseCase,
    val readOnboardingUseCase: ReadOnboardingUseCase
)