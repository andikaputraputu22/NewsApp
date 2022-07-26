package com.moonlightsplitter.newsapp.network

import com.moonlightsplitter.newsapp.models.NewsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("top-headlines")
    suspend fun getNews(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("q") query: String,
        @Query("page") page: Int
    ): NewsModel
}