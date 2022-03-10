package com.example.dictionarycompose.app_functionality.search_word.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dictionarycompose.api.response.Word
import com.example.dictionarycompose.app_functionality.search_word.SearchWordViewModel

@Composable
fun ResultList(
    navController: NavController,
    words: List<Word>,
    viewModel: SearchWordViewModel,
    isRefresh : Boolean
) {
    LazyColumn(
        Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = MaterialTheme.colors.surface)
            .fillMaxWidth()

    ) {
        items(words) { word ->
            WordItem(navController = navController, word = word, viewModel, isRefresh)
            Divider(modifier = Modifier.padding(8.dp), color = MaterialTheme.colors.onSurface)
        }
    }

}