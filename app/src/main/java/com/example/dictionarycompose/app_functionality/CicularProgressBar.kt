package com.example.dictionarycompose.app_functionality

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dictionarycompose.app_functionality.search_word.WordInfoState

@Composable
fun ShowHideProgressBar(
     state : WordInfoState,
     modifier: Modifier
) {
    var circularIndicator by remember {
        mutableStateOf(true)
    }
    if (circularIndicator) {
        CircularProgressIndicator(
            modifier = modifier,
            strokeWidth = 10.dp,
        )
    }
    circularIndicator = state.isLoading
}