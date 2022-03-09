package com.example.dictionarycompose.app_functionality

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    ShowHideProgressBar(
        state = state, modifier = Modifier
            .size(50.dp)
            .fillMaxSize()
    )
    Column {
        ResultList(navController = navController, words =state.wordInfoItems, viewModel, true)
    }
}