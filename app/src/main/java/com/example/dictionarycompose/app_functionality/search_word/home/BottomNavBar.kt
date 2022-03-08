package com.example.dictionarycompose.app_functionality.search_word.home

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(
    navController: NavController
) {
    val bottomNavList = listOf(
        NavigationItem.Home,
        NavigationItem.Favorite,
        NavigationItem.Recent
    )
    BottomNavigation(elevation = 10.dp) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        bottomNavList.forEach {
            BottomNavigationItem(icon = {
                Icon(imageVector = it.icon, it.title)
            },
                label = { Text(text = it.title) },
                selected = currentRoute == it.route,
                selectedContentColor = MaterialTheme.colors.onSurface,
                unselectedContentColor = MaterialTheme.colors.surface,
                alwaysShowLabel = true,
                onClick = {
                    navController.navigate(it.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                })
        }
    }
}