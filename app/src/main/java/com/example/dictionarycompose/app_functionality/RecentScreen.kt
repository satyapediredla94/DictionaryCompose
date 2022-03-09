package com.example.dictionarycompose.app_functionality

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.dictionarycompose.app_functionality.search_word.SearchWordViewModel
import com.example.dictionarycompose.app_functionality.search_word.home.ui.ResultList

@Composable
fun RecentScreen(
    navController: NavController,
    viewModel: SearchWordViewModel
) {
    viewModel.getRecentWords()
    Column {
        val state = viewModel.recentState.value
        ResultList(navController = navController, words =state.wordInfoItems, viewModel)
    }
}