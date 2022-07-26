package com.moonlightsplitter.newsapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.moonlightsplitter.newsapp.fragments.bookmarkFragmentModule
import com.moonlightsplitter.newsapp.fragments.homeFragmentModule
import com.moonlightsplitter.newsapp.network.networkModule
import com.moonlightsplitter.newsapp.repositories.repositoryModule
import com.moonlightsplitter.newsapp.viewmodel.bookmarkViewModelModule
import com.moonlightsplitter.newsapp.viewmodel.homeViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class NewsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@NewsApp)
            modules(
                networkModule,
                repositoryModule,
                homeViewModelModule,
                homeFragmentModule,
                bookmarkViewModelModule,
                bookmarkFragmentModule
            )
        }
    }
}