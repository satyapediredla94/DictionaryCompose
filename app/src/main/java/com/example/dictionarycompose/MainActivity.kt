package com.example.dictionarycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.dictionarycompose.app_functionality.search_word.SearchWordViewModel
import com.example.dictionarycompose.app_functionality.ui.SearchHomeScreen
import com.example.dictionarycompose.ui.theme.DictionaryComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DictionaryComposeTheme {
                val navController = rememberNavController()
                val viewModel: SearchWordViewModel = hiltViewModel()
                SearchHomeScreen(navController, viewModel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DictionaryComposeTheme {

    }
}