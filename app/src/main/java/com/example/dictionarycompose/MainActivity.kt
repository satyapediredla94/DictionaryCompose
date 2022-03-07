package com.example.dictionarycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.dictionarycompose.ui.theme.DictionaryComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DictionaryComposeTheme {

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