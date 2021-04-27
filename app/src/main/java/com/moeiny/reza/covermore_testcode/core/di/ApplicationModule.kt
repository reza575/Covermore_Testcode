package com.moeiny.reza.covermore_testcode.core.di

import android.app.Application
import android.content.Context
import com.moeiny.reza.covermore_testcode.data.retrofit.ApiService
import com.moeiny.reza.covermore_testcode.data.newsrepository.NewsRepository
import com.moeiny.reza.covermore_testcode.data.newsrepository.NewsRepositoryDefault
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {
    @Singleton
    @Provides
    fun provideContext(): Context = application

    @Singleton
    @Provides
    fun provideNewsRepository(newsRepository: NewsRepositoryDefault): NewsRepository = newsRepository

    @Singleton
    @Provides
    fun provideApiService(retrofit:Retrofit):ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.rss2json.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}