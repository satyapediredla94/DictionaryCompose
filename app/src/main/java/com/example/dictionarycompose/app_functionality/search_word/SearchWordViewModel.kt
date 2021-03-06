package com.example.dictionarycompose.app_functionality.search_word

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionarycompose.api.response.Word
import com.example.dictionarycompose.db_impl.WordRepository
import com.example.dictionarycompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchWordViewModel @Inject constructor(
    private val repository: WordRepository
) : ViewModel() {

    private val _displayMatchingWords = MutableStateFlow<List<Word>>(listOf())
    val matchingWords: StateFlow<List<Word>> = _displayMatchingWords

    private val _state = mutableStateOf(WordInfoState().copy(isLoading = false))
    val state: State<WordInfoState> = _state

    private val _word = mutableStateOf<Word?>(null)
    val word: State<Word?> = _word

    private val _favoriteState = mutableStateOf(WordInfoState())
    val favoriteState: State<WordInfoState> = _favoriteState

    private val _recentState = mutableStateOf(WordInfoState())
    val recentState: State<WordInfoState> = _recentState

    private var job: Job? = null

    fun getMatchingWords(word: String) {
        if (word.length > 1) {
            _state.value = _state.value.copy(isLoading = true)
        } else {
            _state.value = _state.value.copy(
                wordInfoItems = emptyList(),
                isLoading = false
            )
            return
        }
        job?.cancel()
        job = viewModelScope.launch {
            delay(2000L)
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
                                wordInfoItems = emptyList(),
                                isLoading = true
                            )
                        }
                    }
                }.launchIn(this)
        }
    }

    fun getFavoriteWords() {
        viewModelScope.launch {
            delay(500L)
            repository.getFavoriteWords()
                .onEach { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            _favoriteState.value = _favoriteState.value.copy(
                                wordInfoItems = resource.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                        is Resource.Error -> {
                            _favoriteState.value = _favoriteState.value.copy(
                                wordInfoItems = resource.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                        is Resource.Loading -> {
                            _favoriteState.value = _favoriteState.value.copy(
                                wordInfoItems = emptyList(),
                                isLoading = true
                            )
                        }
                    }
                }.launchIn(this)
        }
    }

    fun addAsFavorite(word: Word) {
        viewModelScope.launch {
            repository.insertWord(word)
        }
    }

    fun getRecentWords() {
        viewModelScope.launch {
            delay(500L)
            repository.getRecentWords()
                .onEach { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            _recentState.value = _recentState.value.copy(
                                wordInfoItems = resource.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                        is Resource.Error -> {
                            _recentState.value = _recentState.value.copy(
                                wordInfoItems = resource.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                        is Resource.Loading -> {
                            _recentState.value = _recentState.value.copy(
                                wordInfoItems = emptyList(),
                                isLoading = true
                            )
                        }
                    }
                }.launchIn(this)
        }
    }

    fun getMatchingWord(wordJson: String) {
        viewModelScope.launch {
            repository.getWordInfo(wordJson).collect { resource ->
                when (resource) {
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        _word.value = resource.data
                    }
                }
            }
        }
    }

    sealed class UIEvent {
        data class ShowSnackBar(val message: String) : UIEvent()
    }
}