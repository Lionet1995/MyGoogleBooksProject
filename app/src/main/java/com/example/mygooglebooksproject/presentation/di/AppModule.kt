package com.example.mygooglebooksproject.presentation.di

import android.content.Context
import com.example.mygooglebooksproject.domain.usecases.GetBooksByUserEntryUseCase
import com.example.mygooglebooksproject.presentation.adapters.BooksListAdapter
import com.example.mygooglebooksproject.presentation.viewmodels.BooksListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule(val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideBooksListAdapter(): BooksListAdapter {
        val adapter = BooksListAdapter()
        adapter.books = listOf()
        return adapter
    }

    @Provides
    fun provideBooksListViewModelFactory(
        getBooksByUserEntryUseCase: GetBooksByUserEntryUseCase
    ): BooksListViewModelFactory {
        return BooksListViewModelFactory(
            getBooksByUserEntryUseCase = getBooksByUserEntryUseCase
        )
    }
}