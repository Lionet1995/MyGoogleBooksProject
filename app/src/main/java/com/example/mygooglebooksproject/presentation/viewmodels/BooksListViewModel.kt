package com.example.mygooglebooksproject.presentation.viewmodels

import androidx.lifecycle.*
import com.example.mygooglebooksproject.domain.usecases.GetBooksByUserEntryUseCase
import com.example.mygooglebooksproject.domain.models.Book
import com.example.mygooglebooksproject.domain.usecases.GetBooksCountUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class BooksListViewModel(
    private val getBooksByUserEntryUseCase: GetBooksByUserEntryUseCase,
    private val getBooksCountUseCase: GetBooksCountUseCase,
) : ViewModel() {

    private val _booksListMutable = MutableLiveData<List<Book>>()
    val booksList: LiveData<List<Book>> = _booksListMutable

    fun searchBooksByEntry(entry: String) {
        viewModelScope.launch {
            _booksListMutable.value = getBooksByUserEntryUseCase.execute(entry)
        }
    }

    fun getBooksCount(): Flow<Int> = getBooksCountUseCase.execute()
}
