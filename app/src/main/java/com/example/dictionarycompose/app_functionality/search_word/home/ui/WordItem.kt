package com.example.dictionarycompose.app_functionality.search_word.home.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dictionarycompose.api.response.Word
import com.example.dictionarycompose.ui.theme.DictionaryComposeTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WordItem(
    navController: NavController,
    word: Word
) {
    var onFavorite by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = 8.dp
    ) {
        Row(
            Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(0.8f)
                    .padding(8.dp),
            ) {
                Text(text = word.word, style = MaterialTheme.typography.body1)
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
                } ?: "")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "",
                Modifier.weight(0.1f),
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(onClick = { onFavorite = !onFavorite }) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "",
                    Modifier.weight(0.1f),
                    tint = if (onFavorite) Color.Red else Color.Gray
                )
            }
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