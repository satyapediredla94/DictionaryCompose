package com.example.dictionarycompose.app_functionality.search_word

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionarycompose.api.response.Word
import com.example.dictionarycompose.db_impl.WordRepository
import com.example.dictionarycompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchWordViewModel @Inject constructor(
    private val repository: WordRepository
) : ViewModel() {

    private val _displayMatchingWords = MutableLiveData<List<Word>>(listOf())
    val matchingWords: LiveData<List<Word>> = _displayMatchingWords

    private val _state = mutableStateOf(WordInfoState())
    val state: State<WordInfoState> = _state

    private var job: Job? = null

    fun getMatchingWords(word: String) {
        job?.cancel()
        job = viewModelScope.launch {
            delay(500L)
            repository.getMatchingWords(word)
                .onEach { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            _state.value = _state.value.copy(
                                wordInfoItems = resource.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                        is Resource.Error -> {
                            _state.value = _state.value.copy(
                                wordInfoItems = resource.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                        is Resource.Loading -> {
                            _state.value = _state.value.copy(
                                wordInfoItems = resource.data ?: emptyList(),
                                isLoading = true
                            )
                        }
                    }
                }.launchIn(this)
        }
    }

    sealed class UIEvent {
        data class ShowSnackBar(val message: String) : UIEvent()
    }
}