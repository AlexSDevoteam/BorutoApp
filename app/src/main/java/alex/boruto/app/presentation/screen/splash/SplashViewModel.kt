package alex.boruto.app.presentation.screen.splash

import alex.boruto.app.domain.use_cases.UseCases
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {
    private val _onboardingCompleted = MutableStateFlow(false)
    val onboardingCompleted = _onboardingCompleted

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _onboardingCompleted.value =
                useCases.readOnboardingUseCase().stateIn(viewModelScope).value
        }
    }
}