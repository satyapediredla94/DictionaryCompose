package com.example.dictionarycompose.db_impl

import com.example.dictionarycompose.api.DictionaryApi
import com.example.dictionarycompose.api.response.Word
import com.example.dictionarycompose.db.WordDao
import com.example.dictionarycompose.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordRepositoryImpl constructor(
    val api: DictionaryApi,
    private val dao: WordDao
) : WordRepository {

    override suspend fun insertWord(word: Word) = dao.insertWord(word)

    override suspend fun deleteWord(word: Word) = dao.deleteWord(word)

    override suspend fun getWordInfo(word: String): Flow<Resource<Word>> = flow {

        var wordFromDB = dao.getWordInfo(word)
        emit(Resource.Loading())

        try {
            val wordFromRemote = api.getWordInfo(word)
            dao.deleteWord(wordFromDB)
            dao.insertWord(wordFromRemote[0])
        } catch (e: HttpException) {
            emit(Resource.Error(
                message = "Something went wrong on Server. Please try again later."
            ))
        } catch (e: IOException) {
            emit(Resource.Error(
                message = "Something went wrong. Please try again later."
            ))
        }

        wordFromDB = dao.getWordInfo(word)
        emit(Resource.Success(
            data = wordFromDB
        ))
    }

    override suspend fun getWordInfoById(id: Int) : Flow<Resource<Word>> = flow {
        emit(Resource.Success(
            data = dao.getWordInfoById(id)
        ))
    }

    override suspend fun getMatchingWords(word: String): Flow<Resource<List<Word>>> = flow {
         emit(Resource.Success(
             data = dao.getMatchingWords(word)
         ))
    }


}