package com.moonlightsplitter.newsapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class NewsModel(
    val status: String,
    val totalResults: Int,
    val articles: List<DataArticle>
) {
    @Entity(tableName = "tableArticle")
    data class DataArticle(
        val source: DataSource?,
        val author: String?,
        val title: String?,
        val description: String?,
        val url: String?,
        val urlToImage: String?,
        @PrimaryKey(autoGenerate = false)
        val publishedAt: String,
        val content: String?
    ): Serializable {
        data class DataSource(
            val id: String?,
            val name: String?
        ): Serializable
    }
}
