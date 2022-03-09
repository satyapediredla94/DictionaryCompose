package com.example.dictionarycompose.app_functionality.search_word

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    viewModel: SearchWordViewModel
) {
    var searchString by remember {
        mutableStateOf("")
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(Modifier.padding(12.dp)) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = searchString,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                cursorColor = MaterialTheme.colors.onSurface,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                textColor = MaterialTheme.colors.onSurface
            ),
            onValueChange = {
                searchString = it
                if (searchString.isNotEmpty()) {
                    viewModel.getMatchingWords(searchString)
                }
            },
            shape = RoundedCornerShape(32.dp),
            singleLine = true,
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (searchString.isNotEmpty() && searchString.length > 1) {
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
                IconButton(onClick = {

                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = ""
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Go),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                },
                onGo = {
                    keyboardController?.hide()
                    viewModel.getMatchingWords(searchString)
                }
            )
        )
    }
}