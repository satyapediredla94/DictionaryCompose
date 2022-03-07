package com.example.dictionarycompose.api.response

data class Definition(
    val antonyms: List<Any>,
    val definition: String,
    val example: String,
    val synonyms: List<Any>
)