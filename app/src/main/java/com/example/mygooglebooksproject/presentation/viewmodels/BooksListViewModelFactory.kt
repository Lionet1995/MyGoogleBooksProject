package com.example.mygooglebooksproject.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mygooglebooksproject.domain.usecases.GetBooksByUserEntryUseCase
import com.example.mygooglebooksproject.domain.usecases.GetBooksCountUseCase
import com.example.mygooglebooksproject.domain.usecases.GetLastViewedBooksUseCase

class BooksListViewModelFactory (
    private val getBooksByUserEntryUseCase: GetBooksByUserEntryUseCase,
    private val getBooksCountUseCase: GetBooksCountUseCase,
    private val getLastViewedBooksUseCase: GetLastViewedBooksUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BooksListViewModel(
            getBooksByUserEntryUseCase = getBooksByUserEntryUseCase,
            getBooksCountUseCase = getBooksCountUseCase,
            getLastViewedBooksUseCase = getLastViewedBooksUseCase
        ) as T
    }

}