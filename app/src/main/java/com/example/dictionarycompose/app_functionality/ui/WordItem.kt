package com.example.dictionarycompose.app_functionality.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dictionarycompose.api.response.Word
import com.example.dictionarycompose.ui.theme.DictionaryComposeTheme

@Composable
fun WordItem(
    navController: NavController,
    word: Word
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = MaterialTheme.shapes.medium
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
                Text(text = word.word, style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = word.phonetic)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "",
                Modifier.weight(0.1f),
                tint = Color.Gray
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
        SearchHomeScreen(navController = rememberNavController(













        ))
    }
}