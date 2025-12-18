package alex.boruto.app.data.repository

import alex.boruto.app.domain.repository.DataStoreOperations
import javax.inject.Inject

class Repository @Inject constructor(
    private val dataStore: DataStoreOperations
) {
    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed = completed)
    }

    suspend fun readOnBoardingState() = dataStore.readOnBoardingState()
}