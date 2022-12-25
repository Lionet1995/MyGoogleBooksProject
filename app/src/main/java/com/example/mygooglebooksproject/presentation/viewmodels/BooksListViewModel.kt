package com.example.mygooglebooksproject.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mygooglebooksproject.domain.usecases.GetBooksByUserEntryUseCase
import com.example.mygooglebooksproject.domain.models.Book
import kotlinx.coroutines.launch

class BooksListViewModel(
    private val getBooksByUserEntryUseCase: GetBooksByUserEntryUseCase,
): ViewModel() {

    private val _booksListMutable = MutableLiveData<List<Book>>()
    val booksList: LiveData<List<Book>> = _booksListMutable

    fun searchBooksByEntry(entry: String) {
        viewModelScope.launch {
            _booksListMutable.value = getBooksByUserEntryUseCase.execute(entry)
        }
    }
}