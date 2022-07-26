package com.moonlightsplitter.newsapp.viewmodel

import androidx.lifecycle.ViewModel
import com.moonlightsplitter.newsapp.repositories.NewsRepository
import org.koin.dsl.module

val bookmarkViewModelModule = module {
    factory { BookmarkViewModel(get()) }
}

class BookmarkViewModel(
    val repository: NewsRepository
): ViewModel() {

    val title = "Bookmark"
}