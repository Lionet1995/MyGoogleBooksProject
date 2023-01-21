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
            entry.isNullOrEmpty() && result.isEmpty() -> UiState.Error(LOCAL_ERROR)
            entry != null && result.isEmpty() -> UiState.Error(REMOTE_ERROR)
            else -> UiState.Success(result)
        }
    }

    fun getBooksCount(): Flow<Int> = getBooksCountUseCase.execute()

    companion object {
        const val LOCAL_ERROR = "You haven't searched for books yet, use the search"
        const val REMOTE_ERROR = "Something went wrong, try again"
    }
}

sealed class UiState {
    data class Success(val result: List<Book>) : UiState()
    data class Error(val error: String) : UiState()
    object Loading : UiState()
}
