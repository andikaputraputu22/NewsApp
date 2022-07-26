package com.moonlightsplitter.newsapp.repositories

import com.moonlightsplitter.newsapp.BuildConfig
import com.moonlightsplitter.newsapp.models.NewsModel
import com.moonlightsplitter.newsapp.network.ApiInterface
import com.moonlightsplitter.newsapp.utils.COUNTRY_NEWS
import org.koin.dsl.module

val repositoryModule = module {
    factory { NewsRepository(get()) }
}

class NewsRepository(
    private val api: ApiInterface
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
}