package com.example.dictionarycompose.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dictionarycompose.api.MeaningTypeConverter
import com.example.dictionarycompose.api.PhoneticTypeConverter
import com.example.dictionarycompose.api.response.Word

@Database(
    entities = [Word::class],
    version = 1
)
@TypeConverters(MeaningTypeConverter::class, PhoneticTypeConverter::class)
abstract class WordDatabase : RoomDatabase() {
    abstract val wordDao: WordDao
}