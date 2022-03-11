package com.example.dictionarycompose.app_functionality.word_info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dictionarycompose.api.response.Definition
import com.example.dictionarycompose.api.response.Meaning
import com.example.dictionarycompose.api.response.Word
import com.example.dictionarycompose.app_functionality.search_word.SearchWordViewModel
import com.example.dictionarycompose.ui.theme.DictionaryComposeTheme

@Composable
fun WordInfo(
    wordJson: String,
    viewModel: SearchWordViewModel,
    navController: NavController
) {
    viewModel.getMatchingWord(wordJson)
    val word = viewModel.word.value
    DictionaryComposeTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Dictionary")
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    },
                    backgroundColor = MaterialTheme.colors.primary
                )
            },
            content = { paddingValues ->
                Column(
                    Modifier
                        .background(MaterialTheme.colors.primary)
                        .padding(paddingValues = paddingValues)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        text = word?.word ?: "",
                        style = MaterialTheme.typography.h3,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.surface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = word?.phonetic ?: word?.phonetics?.let {
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
                    } ?: "", color = MaterialTheme.colors.surface,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(10.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(16.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colors.background)
                            .verticalScroll(rememberScrollState())
                    ) {
                        TwoTextInAColumn(
                            "Origin :", word?.origin ?: "",
                            modifier = Modifier.padding(start = 8.dp, top = 8.dp)
                        )
                        word?.meanings?.let {
                            if (it.isNotEmpty()) {
                                it.forEach { meaning ->
                                    meaning.definitions?.let {
                                        TwoTextInAColumn(
                                            text1 = "Part of Speech",
                                            text2 = meaning.partOfSpeech ?: "",
                                            modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                                        )
                                        if (meaning.definitions.isNotEmpty()) {
                                            meaning.definitions.forEach { definition ->
                                                Column {
                                                    TwoTextInAColumn(
                                                        text1 = "Definition",
                                                        text2 = definition.definition ?: "",
                                                        modifier = Modifier.padding(
                                                            start = 24.dp,
                                                            top = 8.dp,
                                                            bottom = 8.dp
                                                        )
                                                    )
                                                    if (!definition.example.isNullOrEmpty()) {
                                                        TwoTextInAColumn(
                                                            text1 = "Example",
                                                            text2 = definition.example ?: "",
                                                            modifier = Modifier.padding(
                                                                start = 36.dp,
                                                                top = 8.dp,
                                                                bottom = 8.dp
                                                            )
                                                        )
                                                    }
                                                }
                                            }

                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            }
        )

    }

}

@Composable
fun TwoTextInAColumn(
    text1: String, text2: String,
    modifier: Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = text1,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = text2,
            Modifier.padding(top = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewScreen() {
    val definitions = listOf(
        Definition(
            "used as a greeting or to begin a phone conversation.",
            "hello there, Katie!"
        ),
        Definition(
            "an utterance of ‘hello’; a greeting.",
            "she was getting polite nods and hellos from people"
        )
    )
    val meanaing = listOf(Meaning(definitions, "exclamation"))
    val word = Word(
        meanaing,
        origin = "early 19th century: variant of earlier hollo ; related to holla.",
        phonetic = "həˈləʊ",
        emptyList(),
        "hello"
    )
//    WordInfo(word = word, navController = rememberNavController())
}