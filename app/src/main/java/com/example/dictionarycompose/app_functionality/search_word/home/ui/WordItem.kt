package com.example.dictionarycompose.app_functionality.search_word.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dictionarycompose.api.response.Word
import com.example.dictionarycompose.app_functionality.search_word.SearchWordViewModel
import com.example.dictionarycompose.ui.theme.DictionaryComposeTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WordItem(
    navController: NavController,
    word: Word,
    viewModel: SearchWordViewModel,
    isRefresh: Boolean
) {
    var onFavorite by remember {
        mutableStateOf(false)
    }
    Row(
        Modifier
            .padding(8.dp)
            .clickable {
                navController.navigate("word_info/${word.word}")
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(0.8f)
                .padding(8.dp),
        ) {
            Text(
                text = word.word,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = word.phonetic ?: word.phonetics?.let {
                try {
                    if (it.isNotEmpty()) {
                        var phoneticDisplay = ""
                        for (phonetic in it) {
                            if (!phonetic.text.isNullOrEmpty()) {
                                phoneticDisplay = phonetic.text
                                break
                            }
                        }
                        phoneticDisplay
                    } else {
                        ""
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    ""
                }
            } ?: "", color = MaterialTheme.colors.onSurface)
        }
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(onClick = {
            onFavorite = !onFavorite
            word.isFavorite = onFavorite
            viewModel.addAsFavorite(word)
            if (isRefresh) {
                viewModel.getFavoriteWords()
                viewModel.getRecentWords()
            }
        }) {
            onFavorite = word.isFavorite
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "",
                Modifier.weight(0.1f),
                tint = if (onFavorite) Color.Red else Color.Gray
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DictionaryComposeTheme {
        /*WordItem(
            rememberNavController(),
            word = Word(
                emptyList(),
                "",
                "hello'",
                emptyList(),
                "Hello",
                1
            )
        )*/
//        SearchHomeScreen(navController = rememberNavController())
    }
}