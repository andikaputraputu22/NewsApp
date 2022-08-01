package com.moonlightsplitter.newsapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.moonlightsplitter.newsapp.models.DataArticle
import com.moonlightsplitter.newsapp.models.NewsModel
import com.moonlightsplitter.newsapp.utils.SourceConverter

@Database(
    entities = [DataArticle::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(SourceConverter::class)
abstract class DatabaseClient: RoomDatabase() {
    abstract val newsDao: NewsDao
}