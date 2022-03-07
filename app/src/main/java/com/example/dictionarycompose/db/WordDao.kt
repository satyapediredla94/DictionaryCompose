package com.example.dictionarycompose.db

import androidx.room.*
import com.example.dictionarycompose.api.response.Word

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: Word)

    @Delete
    suspend fun deleteWord(word: Word)

    @Query("SELECT * FROM WORD_INFO WHERE word=:word")
    suspend fun getWordInfo(word: String) : Word

    @Query("SELECT * FROM WORD_INFO WHERE id=:id")
    suspend fun getWordInfoById(id: Int) : Word

    @Query("SELECT * FROM WORD_INFO WHERE word LIKE '%' || :word || '%'")
    suspend fun getMatchingWords(word: String) : List<Word>
}