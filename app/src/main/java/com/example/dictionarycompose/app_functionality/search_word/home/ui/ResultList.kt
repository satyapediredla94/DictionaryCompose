package com.example.dictionarycompose.app_functionality.search_word.home.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.dictionarycompose.api.response.Word
import com.example.dictionarycompose.app_functionality.search_word.SearchWordViewModel

@Composable
fun ResultList(
    navController: NavController,
    words: List<Word>,
    viewModel: SearchWordViewModel
) {

    LazyColumn {
        items(words) { word ->
            WordItem(navController = navController, word = word, viewModel)
        }
    }

}