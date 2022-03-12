package com.example.dictionarycompose.app_functionality

import androidx.compose.foundation.layout.Column
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dictionarycompose.app_functionality.search_word.SearchWordViewModel
import com.example.dictionarycompose.app_functionality.search_word.home.ui.ResultList

@Composable
fun FavoriteScreen(
    navController: NavController,
    viewModel: SearchWordViewModel
) {
    viewModel.getFavoriteWords()
    val state = viewModel.favoriteState.value
    Column {
        if (state.isLoading) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.secondary,
                strokeWidth = 6.dp
            )
        }
        ResultList(navController = navController, words =state.wordInfoItems, viewModel, true)
    }
}