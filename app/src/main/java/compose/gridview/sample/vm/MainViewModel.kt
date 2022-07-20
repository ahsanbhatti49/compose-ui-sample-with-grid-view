package compose.gridview.sample.vm

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import compose.gridview.sample.api.model.MockedModel
import compose.gridview.sample.api.model.Session
import compose.gridview.sample.api.network.Resource
import compose.gridview.sample.api.network.ScreenState
import compose.gridview.sample.repo.MockedDataRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mockedDataRepo: MockedDataRepo
) : ViewModel() {

    val sessionList = mutableStateListOf<Session>()
    lateinit var errorMsg: String
    lateinit var uiState: MutableLiveData<ScreenState>

    init {
        viewModelScope.launch {
            uiState = MutableLiveData()
            getSessions()
        }
    }

    private fun getSessions() {
        mockedDataRepo.getMockedResponse()
            .observeForever {//we can avoid this in future commits
                when (it.status) {
                    is ScreenState.Success -> {
                        sessionList.clear()
                        sessionList.addAll(it.data?.data!!.sessions)
                        uiState.value = ScreenState.Success
                    }
                    is ScreenState.Error -> {
                        errorMsg = it.errorMessage.toString()
                        uiState.value = ScreenState.Error
                    }
                    is ScreenState.Loading -> {
                        uiState.value = ScreenState.Loading
                    }

                }
            }
    }
}