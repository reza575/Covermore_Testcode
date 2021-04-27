package com.moeiny.reza.covermore_testcode.core.di.viewmodel

import androidx.lifecycle.ViewModel
import com.moeiny.reza.covermore_testcode.ui.news.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class BindViewModels {
    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindNewsViewModel(viewModel: NewsViewModel): ViewModel
}