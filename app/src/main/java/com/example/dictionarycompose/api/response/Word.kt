package com.example.dictionarycompose.api.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.dictionarycompose.api.MeaningTypeConverter
import com.example.dictionarycompose.api.PhoneticTypeConverter
import com.example.dictionarycompose.utils.Constants
import com.google.gson.annotations.SerializedName

@Entity(tableName = Constants.WORD)
data class Word(
    @SerializedName("meanings")
    @TypeConverters(MeaningTypeConverter::class)
    val meanings: List<Meaning>?,
    val origin: String?,
    val phonetic: String?,
    @SerializedName("phonetics")
    @TypeConverters(PhoneticTypeConverter::class)
    val phonetics: List<Phonetic>?,
    val word: String
) {
    @PrimaryKey(autoGenerate = true) var id : Int? = null
    var isFavorite: Boolean = false
}