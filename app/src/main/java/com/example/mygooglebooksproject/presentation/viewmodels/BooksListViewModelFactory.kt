package com.example.mygooglebooksproject.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mygooglebooksproject.domain.usecases.GetBooksByUserEntryUseCase

class BooksListViewModelFactory (
    private val getBooksByUserEntryUseCase: GetBooksByUserEntryUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BooksListViewModel(
            getBooksByUserEntryUseCase = getBooksByUserEntryUseCase
        ) as T
    }

}