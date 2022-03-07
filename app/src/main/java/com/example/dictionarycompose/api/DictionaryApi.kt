package com.example.dictionarycompose.api

import com.example.dictionarycompose.api.response.Word
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {

    @GET("api/v2/entries/en/{word}")
    suspend fun getWordInfo(@Path(value = "word") word: String) : List<Word>

    companion object {
        const val BASE_URL = "https://api.dictionaryapi.dev"
    }

}