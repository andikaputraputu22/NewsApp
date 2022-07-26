package com.moonlightsplitter.newsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moonlightsplitter.newsapp.models.CategoryModel
import com.moonlightsplitter.newsapp.models.NewsModel
import com.moonlightsplitter.newsapp.repositories.NewsRepository
import kotlinx.coroutines.launch
import org.koin.dsl.module

val homeViewModelModule = module {
    factory { HomeViewModel(get()) }
}

class HomeViewModel(
    val repository: NewsRepository
): ViewModel() {

    val title = "Berita"
    val categories = listOf<CategoryModel>(
        CategoryModel("", "Berita Utama"),
        CategoryModel("business", "Bisnis"),
        CategoryModel("entertainment", "Hiburan"),
        CategoryModel("general", "Umum"),
        CategoryModel("health", "Kesehatan"),
        CategoryModel("science", "Sains"),
        CategoryModel("sports", "Olahraga"),
        CategoryModel("technology", "Teknologi")
    )

    val category by lazy { MutableLiveData<String>() }
    val message by lazy { MutableLiveData<String>() }
    val news by lazy { MutableLiveData<NewsModel>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    init {
        category.value = ""
        message.value = null
    }

    fun fetchNews() {
        loading.value = true
        viewModelScope.launch {
            try {
                val response = repository.fetchNews(category.value?: "", "", 1)
                news.value = response
                loading.value = false
            } catch (e: Exception) {
                message.value = "Terjadi kesalahan"
                loading.value = false
            }
        }
    }
}