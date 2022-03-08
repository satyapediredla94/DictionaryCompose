package com.example.dictionarycompose.app_functionality.search_word.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(var route: String, var icon: ImageVector, var title: String) {
    object Home : NavigationItem("Home", Icons.Default.Home, "Home")
    object Favorite : NavigationItem("fav", Icons.Default.Star, "Favorites")
    object Recent : NavigationItem("recent",Icons.Default.Refresh, "Recent")
}
