package com.moonlightsplitter.newsapp.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.moonlightsplitter.newsapp.models.DataSource
import com.moonlightsplitter.newsapp.models.NewsModel

object SourceConverter {

    @TypeConverter
    @JvmStatic
    fun toSource(value: String): DataSource {
        val modelType = object : TypeToken<DataSource>() {}.type
        return Gson().fromJson(value, modelType)
    }

    @TypeConverter
    @JvmStatic
    fun fromSource(source: DataSource): String {
        val gson = Gson()
        return gson.toJson(source)
    }
}