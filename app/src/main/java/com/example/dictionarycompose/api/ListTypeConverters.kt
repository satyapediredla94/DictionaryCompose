package com.example.dictionarycompose.api

import androidx.room.TypeConverter
import com.example.dictionarycompose.api.response.Meaning
import com.example.dictionarycompose.api.response.Phonetic
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class MeaningTypeConverter {
    private val gson = Gson()
    @TypeConverter
    fun stringToList(data: String?): List<Meaning> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<Meaning>>() {

        }.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<Meaning>): String {
        return gson.toJson(someObjects)
    }
}

class PhoneticTypeConverter {
    private val gson = Gson()
    @TypeConverter
    fun stringToList(data: String?): List<Phonetic> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<Phonetic>>() {

        }.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<Phonetic>): String {
        return gson.toJson(someObjects)
    }
}