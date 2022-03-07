package com.example.dictionarycompose.db_impl

import com.example.dictionarycompose.api.response.Word
import com.example.dictionarycompose.utils.Resource
import kotlinx.coroutines.flow.Flow

interface WordRepository {

    suspend fun insertWord(word: Word)

    suspend fun deleteWord(word: Word)

    suspend fun getWordInfo(word: String) : Flow<Resource<Word>>

    suspend fun getWordInfoById(id: Int) : Flow<Resource<Word>>

    suspend fun getMatchingWords(word: String) : Flow<Resource<List<Word>>>

}