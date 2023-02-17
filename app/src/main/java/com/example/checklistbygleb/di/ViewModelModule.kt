package com.example.checklistbygleb.di

import androidx.lifecycle.ViewModel
import com.example.checklistbygleb.presentation.CheckItemViewModel
import com.example.checklistbygleb.presentation.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Binds
    fun bindMainViewModel(impl: MainViewModel): ViewModel

    @IntoMap
    @ViewModelKey(CheckItemViewModel::class)
    @Binds
    fun bindCheckItemViewModel(impl: CheckItemViewModel): ViewModel
}