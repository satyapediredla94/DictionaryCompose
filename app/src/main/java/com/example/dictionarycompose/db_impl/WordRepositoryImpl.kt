package com.example.dictionarycompose.db_impl

import com.example.dictionarycompose.api.DictionaryApi
import com.example.dictionarycompose.api.response.Word
import com.example.dictionarycompose.db.WordDao
import com.example.dictionarycompose.utils.Logger
import com.example.dictionarycompose.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordRepositoryImpl constructor(
    val api: DictionaryApi,
    private val dao: WordDao
) : WordRepository {

    companion object {
        const val TAG = "WordRepositoryImpl"
    }

    override suspend fun insertWord(word: Word) = dao.insertWord(word)

    override suspend fun deleteWord(word: Word) = dao.deleteWord(word)

    override suspend fun getWordInfo(word: String): Flow<Resource<Word>> = flow {

        val wordFromDB: Word = dao.getWordInfo(word)
        emit(Resource.Loading())

        try {
            if (wordFromDB == null) {
                Logger.info(TAG, "The word is null. So fetching from API")
                val wordFromRemote = api.getWordInfo(word)
                wordFromRemote[0].timestamp = System.currentTimeMillis()
                dao.insertWord(wordFromRemote[0])
            }
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(
                Resource.Error(
                    message = "Something went wrong on Server. Please try again later."
                )
            )
        } catch (e: IOException) {
            e.printStackTrace()
            emit(
                Resource.Error(
                    message = "Something went wrong. Please try again later."
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            emit(
                Resource.Error(
                    message = e.localizedMessage ?: ""
                )
            )
        }

        val wordFromDb = dao.getWordInfo(word)
        emit(
            Resource.Success(
                data = wordFromDb
            )
        )
    }

    override suspend fun getWordInfoById(id: Int): Flow<Resource<Word>> = flow {
        emit(
            Resource.Success(
                data = dao.getWordInfoById(id)
            )
        )
    }

    override suspend fun getMatchingWords(word: String): Flow<Resource<List<Word>>> = flow {
        getWordInfo(word)
            .collect { resource ->
                if (resource is Resource.Success) {
                    emit(
                        Resource.Success(
                            data = dao.getMatchingWords(word)
                        )
                    )
                }
            }

    }

    override suspend fun getFavoriteWords(): Flow<Resource<List<Word>>> = flow {
        emit(
            Resource.Success(
                data = dao.getFavoriteWords()
            )
        )
    }

    override suspend fun getRecentWords(): Flow<Resource<List<Word>>> = flow {
        emit(Resource.Success(
            data = dao.getRecentWords()
        ))
    }


}