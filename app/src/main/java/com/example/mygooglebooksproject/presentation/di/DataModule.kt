package com.example.mygooglebooksproject.presentation.di

import com.example.mygooglebooksproject.data.api.BooksService
import com.example.mygooglebooksproject.data.repositories.BooksRepository
import com.example.mygooglebooksproject.data.source.BooksNetworkDataSource
import com.example.mygooglebooksproject.data.source.IBooksNetworkDataSource
import com.example.mygooglebooksproject.domain.interfaces.IBooksRepository
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    fun provideBooksNetworkDataSource(booksService: BooksService): IBooksNetworkDataSource {
        return BooksNetworkDataSource(booksService)
    }


    @Provides
    @Singleton
    fun provideBooksRepository(
        booksNetworkDataSource: BooksNetworkDataSource
    ): IBooksRepository {
        return BooksRepository(
            booksNetworkDataSource = booksNetworkDataSource
        )
    }
}