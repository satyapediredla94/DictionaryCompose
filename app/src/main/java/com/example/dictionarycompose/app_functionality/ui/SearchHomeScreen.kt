package com.example.dictionarycompose.app_functionality.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SearchHomeScreen(
    navController: NavController
) {

    Scaffold(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier
            .fillMaxSize(),
    ) {

        var searchString by remember {
            mutableStateOf("")
        }
        Column(Modifier.padding(12.dp)) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = searchString,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.background,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                onValueChange = {
                    searchString = it
                },
                label = {
                    Text(
                        text = "Search",
                        color = Color.Gray
                    )
                },
                shape = RoundedCornerShape(32.dp),
                singleLine = true,
                trailingIcon = {
                    IconButton(
                        onClick = {
                            if (searchString.isNotEmpty()) {
                                searchString = ""
                            }
                        }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = ""
                        )
                    }

                },
                leadingIcon = {
                    IconButton(onClick = { searchString = "" }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = ""
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
            )
        }

    }
}

@Composable
fun SearchBar() {

}

