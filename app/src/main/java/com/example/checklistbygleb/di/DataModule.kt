package com.example.checklistbygleb.di

import android.app.Application
import com.example.checklistbygleb.data.AppDatabase
import com.example.checklistbygleb.data.CheckListDao
import com.example.checklistbygleb.data.CheckListRepositoryImpl
import com.example.checklistbygleb.domain.repository.CheckListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindRepository(impl: CheckListRepositoryImpl): CheckListRepository

    companion object {

        @Provides
        @ApplicationScope
        fun provideCheckListDao(
            application: Application
        ): CheckListDao {
            return AppDatabase.getInstance(application).shopListDao()
        }
    }
}
