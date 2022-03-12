package com.example.dictionarycompose.app_functionality

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dictionarycompose.app_functionality.search_word.SearchWordViewModel
import com.example.dictionarycompose.app_functionality.search_word.home.ui.ResultList

@Composable
fun RecentScreen(
    navController: NavController,
    viewModel: SearchWordViewModel
) {
    viewModel.getRecentWords()
    val state = viewModel.recentState.value
    Column {
        if (state.isLoading) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.secondary,
                strokeWidth = 6.dp,
                modifier = Modifier
                    .height(30.dp)
                    .fillMaxSize()
                    .align(CenterHorizontally)
            )
            viewModel.getRecentWords()
        }
        ResultList(navController = navController, words =state.wordInfoItems, viewModel, true)
    }
}