package com.moeiny.reza.covermore_testcode.core.di

import com.moeiny.reza.covermore_testcode.core.di.viewmodel.BindViewModels
import com.moeiny.reza.covermore_testcode.core.di.viewmodel.ViewModelModule
import com.moeiny.reza.covermore_testcode.ui.content.ContentActivity
import com.moeiny.reza.covermore_testcode.ui.news.NewsActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        ViewModelModule::class,
        BindViewModels::class
    ]
)
interface ApplicationComponent {
    fun injectNewsActivity(activity: NewsActivity)
    fun injectContentActivity(activity: ContentActivity)
}