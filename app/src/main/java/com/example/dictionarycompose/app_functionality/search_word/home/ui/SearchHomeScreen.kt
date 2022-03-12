package com.example.dictionarycompose.app_functionality.search_word.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
        if (state.isLoading) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.secondary,
                strokeWidth = 6.dp,
                modifier = Modifier
                    .height(30.dp)
                    .align(Alignment.CenterHorizontally)
            )
        } else {
            ResultList(navController = navController, words = state.wordInfoItems, viewModel, false)
        }
    }
}


