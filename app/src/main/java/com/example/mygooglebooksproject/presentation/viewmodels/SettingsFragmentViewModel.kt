package com.example.mygooglebooksproject.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mygooglebooksproject.domain.usecases.UpdateBooksCountUseCase
import kotlinx.coroutines.launch

class SettingsFragmentViewModel(
    private val updateBooksCountUseCase: UpdateBooksCountUseCase,
) : ViewModel() {

    var isDataUpdated: () -> Unit = {}

    fun updateBooksCount(count: Int) {
        viewModelScope.launch {
            updateBooksCountUseCase.execute(count)
            isDataUpdated.invoke()
        }
    }
}