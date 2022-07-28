package com.moonlightsplitter.newsapp.repositories

import com.moonlightsplitter.newsapp.BuildConfig
import com.moonlightsplitter.newsapp.database.NewsDao
import com.moonlightsplitter.newsapp.models.NewsModel
import com.moonlightsplitter.newsapp.network.ApiInterface
import com.moonlightsplitter.newsapp.utils.COUNTRY_NEWS
import org.koin.dsl.module

val repositoryModule = module {
    factory { NewsRepository(get(), get()) }
}

class NewsRepository(
    private val api: ApiInterface,
    val db: NewsDao
) {
    suspend fun fetchNews(category: String, query: String, page: Int): NewsModel {
        return api.getNews(
            BuildConfig.API_KEY,
            COUNTRY_NEWS,
            category,
            query,
            page
        )
    }

    suspend fun findNews(article: NewsModel.DataArticle) = db.findNews(article.publishedAt)

    suspend fun saveNews(article: NewsModel.DataArticle) {
        db.saveNews(article)
    }

    suspend fun deleteNews(article: NewsModel.DataArticle) {
        db.deleteNews(article)
    }
}