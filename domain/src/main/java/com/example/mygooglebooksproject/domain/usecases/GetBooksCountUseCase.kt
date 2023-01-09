package com.example.mygooglebooksproject.domain.usecases

import com.example.mygooglebooksproject.domain.interfaces.IUserSettingsRepository
import kotlinx.coroutines.flow.Flow

class GetBooksCountUseCase(private val userSettings: IUserSettingsRepository) {
    fun execute(): Flow<Int> {
        return userSettings.getBooksListCount()
    }
}
