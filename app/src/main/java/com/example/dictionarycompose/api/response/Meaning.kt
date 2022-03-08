package com.example.dictionarycompose.api.response

data class Meaning(
    val definitions: List<Definition>?,
    val partOfSpeech: String?
)