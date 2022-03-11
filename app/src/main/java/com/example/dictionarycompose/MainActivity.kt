package com.example.dictionarycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dictionarycompose.app_functionality.FavoriteScreen
import com.example.dictionarycompose.app_functionality.RecentScreen
import com.example.dictionarycompose.app_functionality.search_word.SearchWordViewModel
import com.example.dictionarycompose.app_functionality.search_word.home.BottomBar
import com.example.dictionarycompose.app_functionality.search_word.home.NavigationItem
import com.example.dictionarycompose.app_functionality.search_word.home.ui.SearchHomeScreen
import com.example.dictionarycompose.app_functionality.word_info.WordInfo
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
                                Text(
                                    text = "Dictionary".uppercase(),
                                    style = MaterialTheme.typography.h6,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
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
                    content = { innerPadding ->
                        // Apply the padding globally to the whole BottomNavScreensController
                        Box(modifier = Modifier.padding(innerPadding)) {
                            Navigation(navController = navController)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    val viewModel: SearchWordViewModel = hiltViewModel()
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            SearchHomeScreen(navController, viewModel)
        }
        composable(NavigationItem.Favorite.route) {
            FavoriteScreen(navController, viewModel)
        }
        composable(NavigationItem.Recent.route) {
            RecentScreen(navController, viewModel)
        }
        composable("word_info/{word}",
            arguments = listOf(
                navArgument("word") {
                    // Make argument type safe
                    type = NavType.StringType
                    defaultValue = ""
                }
            )) { backStackEntry ->
            val wordJson = backStackEntry.arguments?.get("word")
            WordInfo(
                navController = navController,
                viewModel = viewModel,
                wordJson = wordJson as String
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DictionaryComposeTheme {

    }
}