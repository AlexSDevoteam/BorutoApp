package alex.boruto.app.presentation.screen.home

import alex.boruto.app.domain.use_cases.UseCases
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    useCases: UseCases
): ViewModel() {
    val getAllHeroes = useCases.getAllHeroesUseCase()
}