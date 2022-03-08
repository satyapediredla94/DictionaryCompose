package com.example.dictionarycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dictionarycompose.app_functionality.search_word.SearchWordViewModel
import com.example.dictionarycompose.app_functionality.search_word.home.BottomBar
import com.example.dictionarycompose.app_functionality.search_word.home.NavigationItem
import com.example.dictionarycompose.app_functionality.search_word.home.ui.SearchHomeScreen
import com.example.dictionarycompose.ui.theme.DictionaryComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DictionaryComposeTheme {
                val navController = rememberNavController()
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "Dictionary".uppercase(),
                                    style = MaterialTheme.typography.h6,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center)
                            },
                            /*navigationIcon = {
                                IconButton(onClick = {}) {
                                    Icon(Icons.Filled.ArrowBack, "backIcon")
                                }
                            },*/
                            backgroundColor = MaterialTheme.colors.primary,
                            elevation = 0.dp,
                        )
                    },
                    bottomBar = { BottomBar(navController) },
                    backgroundColor = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    Navigation(navController = navController)
                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            val viewModel: SearchWordViewModel = hiltViewModel()
            SearchHomeScreen(navController, viewModel)
        }
        composable(NavigationItem.Favorite.route) {

        }
        composable(NavigationItem.Recent.route) {
//            MoviesScreen()
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DictionaryComposeTheme {

    }
}