package com.example.dictionarycompose.app_functionality.search_word.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.dictionarycompose.app_functionality.search_word.SearchBar
import com.example.dictionarycompose.app_functionality.search_word.SearchWordViewModel

@Composable
fun SearchHomeScreen(
    navController: NavController,
    viewModel: SearchWordViewModel
) {
    Column {
        SearchBar(viewModel)
        val state = viewModel.state.value
        ResultList(navController = navController, words = state.wordInfoItems, viewModel, false)
    }
}


