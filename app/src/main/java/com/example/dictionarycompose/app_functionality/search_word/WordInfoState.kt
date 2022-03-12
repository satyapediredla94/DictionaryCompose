package com.example.dictionarycompose.app_functionality.search_word

import com.example.dictionarycompose.api.response.Word

data class WordInfoState(
    val wordInfoItems: List<Word> = emptyList(),
    val isLoading: Boolean = true
)
