package com.example.dictionarycompose.di

import android.content.Context
import androidx.room.Room
import com.example.dictionarycompose.api.DictionaryApi
import com.example.dictionarycompose.db.WordDatabase
import com.example.dictionarycompose.db_impl.WordRepository
import com.example.dictionarycompose.db_impl.WordRepositoryImpl
import com.example.dictionarycompose.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesDictionaryApi() : DictionaryApi = Retrofit.Builder()
        .baseUrl(DictionaryApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DictionaryApi::class.java)


    @Provides
    @Singleton
    fun providesWordDb(
        @ApplicationContext context: Context
    ) : WordDatabase = Room.databaseBuilder(
        context,
        WordDatabase::class.java,
        Constants.DB_NAME
    ).build()

    @Provides
    @Singleton
    fun providesRepository(
        db: WordDatabase,
        api: DictionaryApi
    ): WordRepository = WordRepositoryImpl(api, db.wordDao)

}