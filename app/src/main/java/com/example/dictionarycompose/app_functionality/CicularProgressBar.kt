package com.example.dictionarycompose.app_functionality

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShowHideProgressBar(
     modifier: Modifier
) {

        CircularProgressIndicator(
            modifier = modifier,
            strokeWidth = 10.dp,
        )
}