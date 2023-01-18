package com.example.mygooglebooksproject.presentation.di

import android.content.Context
import com.example.mygooglebooksproject.data.storages.BooksDatabase
import com.example.mygooglebooksproject.data.storages.getBooksDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDBModule {

    @Provides
    @Singleton
    fun provideDB(context: Context): BooksDatabase {
        return getBooksDatabase(context)
    }
}