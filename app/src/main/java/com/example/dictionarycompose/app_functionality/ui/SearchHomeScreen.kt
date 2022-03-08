package com.example.dictionarycompose.app_functionality.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.dictionarycompose.app_functionality.search_word.SearchBar
import com.example.dictionarycompose.app_functionality.search_word.SearchWordViewModel

@Composable
fun SearchHomeScreen(
    navController: NavController,
    viewModel: SearchWordViewModel
) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column {
            SearchBar(viewModel)
            val state = viewModel.state.value
            ResultList(navController = navController, words =state.wordInfoItems)
        }
    }
}


