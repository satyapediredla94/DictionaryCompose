package com.example.dictionarycompose.app_functionality.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.dictionarycompose.api.response.Word

@Composable
fun ResultList(
    navController: NavController,
    words: List<Word>
) {

    LazyColumn {
        items(words) { word ->
            WordItem(navController = navController, word = word)
        }
    }

}