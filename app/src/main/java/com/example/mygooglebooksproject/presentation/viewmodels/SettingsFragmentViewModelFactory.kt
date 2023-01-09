package com.example.mygooglebooksproject.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mygooglebooksproject.domain.usecases.UpdateBooksCountUseCase

class SettingsFragmentViewModelFactory(
    private val updateBooksCountUseCase: UpdateBooksCountUseCase
): ViewModelProvider.Factory  {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SettingsFragmentViewModel(
            updateBooksCountUseCase = updateBooksCountUseCase
        ) as T
    }
}