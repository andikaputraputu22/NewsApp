package com.moonlightsplitter.newsapp.models

import java.io.Serializable

data class NewsModel(
    val status: String,
    val totalResults: Int,
    val articles: List<DataArticle>
) {
    data class DataArticle(
        val source: DataSource?,
        val author: String?,
        val title: String?,
        val description: String?,
        val url: String?,
        val urlToImage: String?,
        val publishedAt: String,
        val content: String?
    ): Serializable {
        data class DataSource(
            val id: String?,
            val name: String?
        ): Serializable
    }
}
