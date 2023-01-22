package com.example.mygooglebooksproject.presentation.viewmodels

import androidx.lifecycle.*
import com.example.mygooglebooksproject.domain.usecases.GetBooksByUserEntryUseCase
import com.example.mygooglebooksproject.domain.models.Book
import com.example.mygooglebooksproject.domain.usecases.GetBooksCountUseCase
import com.example.mygooglebooksproject.domain.usecases.GetLastViewedBooksUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class BooksListViewModel(
    private val getBooksByUserEntryUseCase: GetBooksByUserEntryUseCase,
    private val getBooksCountUseCase: GetBooksCountUseCase,
    private val getLastViewedBooksUseCase: GetLastViewedBooksUseCase,
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    init {
        getBooksList()
    }

    fun getBooksList(entry: String? = null) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            val result: List<Book> = if (entry.isNullOrEmpty()) {
                getLastViewedBooksUseCase.execute()
            } else {
                getBooksByUserEntryUseCase.execute(entry)
            }

            updateUiState(entry, result)
        }
    }

    private fun updateUiState(entry: String?, result: List<Book>) {
        _uiState.value = when {
            entry.isNullOrEmpty() && result.isEmpty() -> UiState.Empty
            entry != null && result.isEmpty() -> UiState.NoResults
            else -> UiState.Success(result)
        }
    }

    fun getBooksCount(): Flow<Int> = getBooksCountUseCase.execute()
}

sealed class UiState {
    data class Success(val result: List<Book>) : UiState()
    object Empty : UiState()
    object NoResults : UiState()
    object Loading : UiState()
    //add error state
}
