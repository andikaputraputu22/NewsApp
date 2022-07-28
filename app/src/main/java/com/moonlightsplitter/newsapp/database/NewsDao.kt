package com.moonlightsplitter.newsapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.moonlightsplitter.newsapp.models.NewsModel

@Dao
interface NewsDao {

    @Query("SELECT * FROM tableArticle")
    fun getAllNews(): LiveData<List<NewsModel.DataArticle>>

    @Query("SELECT COUNT(*) FROM tableArticle WHERE publishedAt=:publish")
    suspend fun findNews(publish: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNews(article: NewsModel.DataArticle)

    @Delete
    suspend fun deleteNews(article: NewsModel.DataArticle)
}