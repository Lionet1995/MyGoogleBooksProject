package com.example.mygooglebooksproject.presentation.di

import android.content.Context
import com.example.mygooglebooksproject.domain.usecases.GetBooksByUserEntryUseCase
import com.example.mygooglebooksproject.domain.usecases.GetBooksCountUseCase
import com.example.mygooglebooksproject.domain.usecases.GetLastViewedBooksUseCase
import com.example.mygooglebooksproject.domain.usecases.UpdateBooksCountUseCase
import com.example.mygooglebooksproject.presentation.adapters.BooksListAdapter
import com.example.mygooglebooksproject.presentation.viewmodels.BooksListViewModelFactory
import com.example.mygooglebooksproject.presentation.viewmodels.SettingsFragmentViewModelFactory
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
        getBooksByUserEntryUseCase: GetBooksByUserEntryUseCase,
        getBooksCountUseCase: GetBooksCountUseCase,
        getLastViewedBooksUseCase: GetLastViewedBooksUseCase
    ): BooksListViewModelFactory {
        return BooksListViewModelFactory(
            getBooksByUserEntryUseCase = getBooksByUserEntryUseCase,
            getBooksCountUseCase = getBooksCountUseCase,
            getLastViewedBooksUseCase = getLastViewedBooksUseCase
        )
    }

    @Provides
    fun provideSettingsFragmentViewModelFactory(
        updateBooksCountUseCase: UpdateBooksCountUseCase
    ): SettingsFragmentViewModelFactory {
        return SettingsFragmentViewModelFactory(
            updateBooksCountUseCase = updateBooksCountUseCase
        )
    }
}