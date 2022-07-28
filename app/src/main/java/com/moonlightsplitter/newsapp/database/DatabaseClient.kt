package com.moonlightsplitter.newsapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moonlightsplitter.newsapp.models.NewsModel

@Database(
    entities = [NewsModel.DataArticle::class],
    version = 1,
    exportSchema = false
)
abstract class DatabaseClient: RoomDatabase() {
    abstract val newsDao: NewsDao
}